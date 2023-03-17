package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab2.databinding.FragmentLevelUpBinding

class LevelUpFragment : Fragment() {

    private lateinit var binding: FragmentLevelUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLevelUpBinding.inflate(layoutInflater, container, false)
        val view = binding.root







        return view
    }


}