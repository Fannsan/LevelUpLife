package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.LevelUpLife.databinding.FragmentLoggedInHomeBinding


class LoggedInHomeFragment : Fragment() {

    lateinit var binding: FragmentLoggedInHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding = FragmentLoggedInHomeBinding.inflate(layoutInflater,container,false)
       val view =  binding.root


        val btnToLevelUp = binding.btnGoToLevelUp
        val btnToAdvice = binding.btnGoToAdvice
        val btnUserProfile = binding.btnUserProfile

        val btnUserGoal = binding.btnUserGoal


        btnToLevelUp.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loggedInHomeFragment_to_levelUpFragment)
        }

        btnToAdvice.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loggedInHomeFragment_to_adviceFragment)
        }

        btnUserProfile.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loggedInHomeFragment_to_userProfileFragment)
        }

        btnUserGoal.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loggedInHomeFragment_to_goalFragment)
        }

        return view
    }


}