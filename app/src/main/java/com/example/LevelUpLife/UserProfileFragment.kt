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
import com.example.LevelUpLife.LevelUp.UserViewModel
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentCreateAccountBinding
import com.example.LevelUpLife.databinding.FragmentUserProfileBinding
import com.google.firebase.database.*
import kotlinx.coroutines.launch

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root


        val etChangeUsername = binding.etChangeUserName
        val btnChangeUsername = binding.btnChangeUsername
        val etPassword = binding.etPassword
        val etOldUserName = binding.etOldUserName
        val etEmail = binding.etUserEmail
        val etUserPasssword = binding.etUserPassword

        val btnDeleteUser = binding.btnDeleteUser

        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")


        val userEmail = binding.tvUserName

        val viewModel: UserViewModel by viewModels()

        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect(){ uiState ->

                    userEmail.text = uiState.userEmail
                    println(uiState.userEmail)
                }
            }
        }


        //Inizialise viewmodel
       // viewModel = ViewModelProvider(requireActivity())[UserViewModel().javaClass]
       // val email = viewModel.getuserEmail()
       // userEmail.text = email


        btnChangeUsername.setOnClickListener {
            val oldUsername = etOldUserName.text.toString()
            val username = etChangeUsername.text.toString()
            val password = etPassword.text.toString()
            db.orderByChild("username").equalTo(oldUsername).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        //Going trough all the index of the children
                        for (userSnapshot in snapshot.children) {
                            val dbUser = userSnapshot.getValue(Users::class.java)

                            if (dbUser != null && dbUser.password == password) {

                                userSnapshot.ref.child("username").setValue(username)

                                    .addOnSuccessListener {
                                        binding.etChangeUserName.text.clear()
                                        Toast.makeText(
                                            requireContext(),
                                            "You successfully changed your username",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }

                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Wrong password or username",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }


                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Your username does not exist, try again",
                            Toast.LENGTH_LONG
                        ).show()
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


        btnDeleteUser.setOnClickListener{
        /*val email = etEmail.text.toString()
            val userPassword = etUserPasssword.text.toString()
            db.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){

                        for (userSnapshot in snapshot.children){
                            val dbUser = userSnapshot.getValue(Users::class.java)
                            if (dbUser != null && dbUser.password == userPassword) {

                                userSnapshot.ref.child("email")

                                    .addOnSuccessListener {
                                        binding.etChangeUserName.text.clear()
                                        Toast.makeText(
                                            requireContext(),
                                            "You successfully deleted your profile",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }

                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Wrong password or email",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }


                    } else{
                        Toast.makeText(
                            requireContext(),
                            "Your email and password does not exist, try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })

*/
        }

       /* lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect(){
             val email = viewModel.getUserEmail()
                    userEmail = email
                }
            }
        }*/

        return view
    }
}



