package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import com.example.LevelUpLife.databinding.FragmentNewsletterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


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

        val etUsername = binding.etUserName
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

           val username = etUsername.text.toString()
           val password = etPassword.text.toString()

            //Creating a new User
           val newUser = Users(username, password, isRegistred = true)

            db.push()
                .setValue(newUser)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(),"hello",Toast.LENGTH_LONG).show()
                }




        }


        return view
    }


}