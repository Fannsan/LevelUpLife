package com.example.LevelUpLife.LevelUp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.LevelUpLife.R
import com.example.LevelUpLife.databinding.ItemBinding

class GoalAdapter (

    private val goals: List<GoalUIState>,

        ): RecyclerView.Adapter<GoalAdapter.GoalViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
 val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return GoalViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return goals.size

    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val currentList = goals[position]
        holder.name.text = currentList.goalName
    }

    class GoalViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)

    }
}
