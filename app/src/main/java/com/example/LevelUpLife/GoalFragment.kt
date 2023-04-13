package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.LevelUpLife.databinding.FragmentGoalBinding
import com.example.LevelUpLife.databinding.FragmentLoginBinding


class GoalFragment : Fragment() {

    private lateinit var binding: FragmentGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentGoalBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val btnNewGoal = binding.fabNewGoal

        btnNewGoal.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_goalFragment_to_newGoalFragment)
        }


        return view
    }


}