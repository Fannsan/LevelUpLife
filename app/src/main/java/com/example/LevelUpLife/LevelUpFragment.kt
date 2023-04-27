package com.example.LevelUpLife

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import com.example.LevelUpLife.LevelUp.LevelUpViewModel
import com.example.LevelUpLife.databinding.FragmentLevelUpBinding
import kotlinx.coroutines.launch
import retrofit2.*

class LevelUpFragment : Fragment() {

    private lateinit var binding: FragmentLevelUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLevelUpBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        // Get references to the components in the layout
        val tvQuote = binding.tvQuote
        val btnExcited = binding.btnExcited
        val btnTired = binding.btnTired
        val btnAnxious = binding.btnAnxious
        val btnIrritated = binding.btnIrritated
        val btnHome = binding.fabHome

        //Instantiate viewModel
        val viewModel: LevelUpViewModel by viewModels()


        //Navigate to home
        btnHome.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_levelUpFragment_to_loggedInHomeFragment)
        }

        //Buttons for every mood
        btnExcited.setOnClickListener(){

            //fetching my quote from the chosen array
            viewModel.setExcitedQuote().toString()

        }

        btnTired.setOnClickListener(){

            viewModel.setTiredQuote()

        }

        btnAnxious.setOnClickListener() {


            viewModel.setAnxiousQuote()
        }

        btnIrritated.setOnClickListener(){

             viewModel.setIrritatedQuote()

        }


        //My lifecycleScope for when my text change with a new quote
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect() {
                    val quote = viewModel.uiState.value.myList.toString()

                    //substring get rid of first and last signs of the array  which is the "["
                    tvQuote.text = quote.substring(1,quote.length-1)

                }
            }
        }

        tvQuote.setOnClickListener{

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Add qoute to your list")
            alert.setMessage("Do you want to add this quote to your list?")

            alert.setPositiveButton("Yes") {_, _ ->

             tvQuote.text = viewModel.uiState.value.myList.toString()


        }
        }




        return view
    }


}