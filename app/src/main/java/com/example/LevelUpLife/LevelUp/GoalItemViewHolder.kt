package com.example.LevelUpLife.LevelUp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.LevelUpLife.databinding.ItemBinding

class GoalItemViewHolder(
    private val context: Context,
    private val binding: ItemBinding,
): RecyclerView.ViewHolder(binding.root) {

    fun bindGoalItem(goalItem: GoalUIState) {
        binding.name.text = goalItem.goalName

    }
}