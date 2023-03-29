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

        val btnToAboutFragment = binding.btnGoToAbout

        val btnLevelUp = binding.btnGoToLevelUp

        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }

        //Navigate to About
        btnToAboutFragment.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        //Navigate to Level UP
        btnLevelUp.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_levelUpFragment)
        }



       return view
    }



}