package com.example.LevelUpLife

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.GoalViewModel
import com.example.LevelUpLife.databinding.FragmentGoalBinding
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class GoalFragment : Fragment() {

    private lateinit var binding: FragmentGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentGoalBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val viewModel: GoalViewModel by viewModels()

        val btnNewGoal = binding.fabNewGoal

        val texten = binding.goal



        btnNewGoal.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_goalFragment_to_newGoalFragment)


        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.goalUiState.collect {
                    val goals = viewModel.goalUiState.value.goalList.toString()

                    texten.text = goals
                }
            }
        }


        return view
    }


}
