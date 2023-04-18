package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.GoalViewModel
import com.example.LevelUpLife.LevelUp.LevelUpViewModel
import com.example.LevelUpLife.LevelUp.UserViewModel
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import com.example.LevelUpLife.databinding.FragmentNewsletterBinding
import com.google.firebase.database.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    //setup binding
    private lateinit var binding: FragmentLoginBinding

    //initialize database
    private lateinit var db: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")

        val etEmail = binding.etEmail
        val etPassword = binding.etPassword
        val btnSignIn = binding.btnSignIn
        val fabHome = binding.fabHome
        val tvSignUp = binding.tvSignUp

        val viewModel: UserViewModel by viewModels()


        var listOfUsers = arrayListOf<Users>()

        fabHome.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
        }

        tvSignUp.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_createAccountFragment)
        }



        btnSignIn.setOnClickListener{

           val email = etEmail.text.toString()
           val password = etPassword.text.toString()

            db.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        //Going trough all the index of the children
                        for(userSnapshot in snapshot.children) {
                            val dbUser = userSnapshot.getValue(Users::class.java)

                            if (dbUser != null && dbUser.password == password) {

                                /*Save email in UserViewModel if user is logged in
                                lifecycleScope.launch{
                                    repeatOnLifecycle(Lifecycle.State.STARTED){
                                        viewModel.uiState.collect(){
                                            viewModel.loggedInUser(email = userEmail)

                                        }
                                    }
                                }*/


                                //viewModel.loggedInUser(email)

                                // Using bundle to send over the email of the user when they log in
                                val bundle = Bundle()
                                bundle.putString("email",email)

                                Navigation.findNavController(view)
                                    .navigate(R.id.action_loginFragment_to_userProfileFragment, bundle)



                                Toast.makeText(
                                    requireContext(),
                                    "You successfully logged in ",
                                    Toast.LENGTH_LONG
                                ).show()


                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Wrong password or email",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }


                    } else {
                        Toast.makeText(requireContext(),"Your email does not exist, try again or register a new account", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        "Failure: something went wrong with the database connection",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })




        }


        return view
    }


}