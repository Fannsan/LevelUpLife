package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.UserViewModel
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import com.google.firebase.database.*


class LoginFragment : Fragment() {

    //setup binding
    private lateinit var binding: FragmentLoginBinding

    //initialize database
    private lateinit var db: DatabaseReference

    //initialize a relation bound viewModel with activityViewModel
    private val viewModel : UserViewModel by activityViewModels()

    //private val goalViewModel : GoalViewModel by activityViewModels()

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

        //connection to my database
        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")

        val etEmail = binding.etEmail
        val etPassword = binding.etPassword
        val btnSignIn = binding.btnSignIn
        val fabHome = binding.fabHome
        val tvSignUp = binding.tvSignUp


       var listOfUsers = arrayListOf<Users>()



        fabHome.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
        }

        tvSignUp.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_createAccountFragment)
        }



        btnSignIn.setOnClickListener{
            //set userInput to a variable to check against the database
           val email = etEmail.text.toString()
           val password = etPassword.text.toString()

            //Check if email in the databas is the same as userInput email
            db.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        //Going trough all the index of the children
                        for(userSnapshot in snapshot.children) {
                            val dbUser = userSnapshot.getValue(Users::class.java)

                            if (dbUser != null && dbUser.password == password) {

                                /*
                                val currentUser = userSnapshot.key?.let { it1 ->


                                    //viewModel.setCurrentUser(userName = dbUser.username.toString(), email = dbUser.email.toString(), password = dbUser.password.toString(), goal = dbUser.goal.toString(), goalList = dbUser.goalList)


                                    Users(
                                        username = dbUser.username.toString(),
                                        email = dbUser.email.toString(),
                                        password = dbUser.password.toString(),
                                        goal = dbUser.goal.toString(),
                                        goalList = dbUser.goalList,
                                        id = it1

                                    )

                                }

                                 */

                                viewModel.setCurrentUser(username = dbUser.username.toString(), email = dbUser.email.toString(), password = dbUser.password.toString(), goal = dbUser.goal.toString(), goalList = dbUser.goalList, id = userSnapshot.key.toString())



                                // Using bundle to send over the email of the user when they log in
                               // val bundle = Bundle()
                               // bundle.putString("email",email)

                                //Save email in UI state with UserViewModel if user is logged in


                                //viewModel.loggedInUser(email)




                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_userProfileFragment, )



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