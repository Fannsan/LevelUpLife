package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import com.example.LevelUpLife.databinding.FragmentNewsletterBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

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

        val etUsername = binding.etUserName
        val etPassword = binding.etPassword
        val btnSignIn = binding.btnSignIn
        val fabHome = binding.fabHome

        fabHome.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
        }



        btnSignIn.setOnClickListener{

           val username = etUsername.text.toString()
           val password = etPassword.text.toString()

        }


        return view
    }


}