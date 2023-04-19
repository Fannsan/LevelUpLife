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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.LevelUpLife.LevelUp.GoalAdapter
import com.example.LevelUpLife.LevelUp.GoalUIState
import com.example.LevelUpLife.LevelUp.GoalViewModel
import com.example.LevelUpLife.databinding.FragmentGoalBinding
import com.example.LevelUpLife.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class GoalFragment : Fragment() {

    private lateinit var binding: FragmentGoalBinding

    private lateinit var viewModel: GoalViewModel

    private lateinit var goalAdapter: GoalAdapter

    private lateinit var recyclerView: RecyclerView

    //private lateinit var itemList:  ArrayList<GoalUIState>
    private var goalList = mutableListOf<GoalUIState>()

    lateinit var goalName : Array<String>
    lateinit var goals : Array<String>

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


        val etGoalName = binding.etGoalName
        val etGoalDesc = binding.etGoalDescription
        val btnSave = binding.btnSave
        val goalList = binding.goalList

        // val viewModel: GoalViewModel by viewModels()
        viewModel = ViewModelProvider(this)[GoalViewModel().javaClass]
        goalList.text = viewModel.toString()

        //Show and save the data in my Goal viewModel when you click on the button
        btnSave.setOnClickListener(){
            val goalName = etGoalName.text.toString()
            val goalDesc = etGoalDesc.text.toString()


            //viewModel.addNewGoal(goalList)
            goalList.text = viewModel.getGoalListAsString()
            etGoalName.setText("")


        }

        return view
    }


}
