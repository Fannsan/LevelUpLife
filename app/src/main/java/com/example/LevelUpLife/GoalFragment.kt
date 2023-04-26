package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.LevelUpLife.LevelUp.Goal
import com.example.LevelUpLife.LevelUp.UserViewModel
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentGoalBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class GoalFragment : Fragment() {

    lateinit var binding: FragmentGoalBinding

    //initialize database
    private lateinit var db: DatabaseReference

    //initialize a relation bound viewModel with activityViewModel
    //private val goalViewModel : GoalViewModel by activityViewModels()

    private val viewModel : UserViewModel by activityViewModels()


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

        val etGoal = binding.etGoal

        val btnSetGoal = binding.btnSetGoal

        val tvGoals = binding.tvShowGoal

        //variable for my Goal class
        var mygoal : Goal
        val goalList = ArrayList<Goal>()

        //Fetching my db connection


        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")




        btnSetGoal.setOnClickListener{

            //variabel for my edittext
            val newgoal = etGoal.text.toString()

            mygoal = Goal(newgoal)
            goalList.add(mygoal)



            //Creating a refVariabel to get the userId and making a pathString with Goals
            val newGoalRef = db.child(viewModel.uiState.value.id!!).child("Goals")

            newGoalRef
                .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(datasnapshot: DataSnapshot) {

                    val existingGoals = datasnapshot.getValue<ArrayList<Goal>>()
                    if (existingGoals != null) {
                        existingGoals.add(mygoal)
                        newGoalRef.setValue(existingGoals)
                    } else {
                        val newGoalsList = ArrayList<Goal>()
                        newGoalsList.add(mygoal)
                        newGoalRef.setValue(newGoalsList)
                    }
                    // Update the UI with the updated list of goals
                    tvGoals.text = goalList.toString()
                }



                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }

            )

        }

        return view
    }


}