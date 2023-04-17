package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.GoalViewModel
import com.example.LevelUpLife.databinding.FragmentCreateAccountBinding
import com.example.LevelUpLife.databinding.FragmentNewGoalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NewGoalFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewGoalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewGoalBinding.inflate(layoutInflater,container,false)
        val view = binding.root


        val etInputGoal = binding.etInputGoal
        val etPoints = binding.etPoints
        val btnAddGoal = binding.btnAddGoal

        val goalViewModel: GoalViewModel by viewModels()

        btnAddGoal.setOnClickListener{

         val newGoal = etInputGoal.text.toString()
         goalViewModel.addNewGoal(newGoal)

           Navigation.findNavController(view).navigate(R.id.action_newGoalFragment_to_goalFragment)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}