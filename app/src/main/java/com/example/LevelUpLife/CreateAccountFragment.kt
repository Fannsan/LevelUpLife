package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentCreateAccountBinding
import com.google.firebase.database.DatabaseReference


class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    private lateinit var db: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentCreateAccountBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val etCreateUserName = binding.etNewUserName
        val etCreatePassword = binding.etNewPassword
        val btnCreateAcount = binding.btnCreateAccount
        val fabHome = binding.fabHome


        //Navigate to Home
        fabHome.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
        }


        btnCreateAcount.setOnClickListener{

            val username = etCreateUserName.text.toString()
            val password = etCreatePassword.text.toString()

            //Creating a new User
            val newUser = Users(username, password, isRegistred = true)

            db.push()
                .setValue(newUser)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(),"You made it", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Toast.makeText(requireContext(),"Failure: something went wrong $it", Toast.LENGTH_LONG).show()
                }






        }



        return view
    }


}