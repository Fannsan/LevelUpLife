package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.GoalViewModel
import com.example.LevelUpLife.databinding.FragmentCreateAccountBinding
import com.example.LevelUpLife.databinding.FragmentNewGoalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText


class NewGoalFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewGoalBinding
    private lateinit var goalViewModel: GoalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentNewGoalBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        goalViewModel = ViewModelProvider(this)[GoalViewModel::class.java]


        val etInputGoal = binding.etGoalName
        val etdesc = binding.etGoalDescription
        val btnSave = binding.btnSave



        val goalViewModel: GoalViewModel by viewModels()




        return view
    }




}