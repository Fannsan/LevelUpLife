package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.LevelUpLife.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val view = binding.root



       val btnLogin = binding.btnLogin
       val btnNewsLetter = binding.btnGoToSignUpNewsletter



        btnLogin.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }

        btnNewsLetter.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newsletterFragment)
        }



       return view
    }



}