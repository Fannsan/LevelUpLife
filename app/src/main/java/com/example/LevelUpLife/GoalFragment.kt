package com.example.LevelUpLife

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.GoalViewModel
import com.example.LevelUpLife.databinding.FragmentGoalBinding
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class GoalFragment : Fragment() {

    private lateinit var binding: FragmentGoalBinding

    private var frag: NewGoalFragment? = null

    //private lateinit var auth: FirebaseAuth

    private lateinit var viewModel: GoalViewModel

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


        val btnNewGoal = binding.fabNewGoal

        val texten = binding.goal

        val etInputGoal = binding.etInputGoal

        val btnAddGoal = binding.btnAddGoal

        // val viewModel: GoalViewModel by viewModels()
        viewModel = ViewModelProvider(this)[GoalViewModel().javaClass]
        texten.text = viewModel.toString()



        btnAddGoal.setOnClickListener(){
            val newGoal = etInputGoal.text.toString()
            viewModel.addNewGoal(newGoal)
            texten.text = viewModel.getGoalListAsString()
            etInputGoal.setText("")
        }
        texten.text = viewModel.getGoalListAsString()


        btnNewGoal.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_goalFragment_to_newGoalFragment)


        }
/*
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.goalUiState.collect {
                    val goals = viewModel.goalUiState.value.goalList.toString()

                    texten.text = goals
                }
            }
        }
*/

        return view
    }


}
