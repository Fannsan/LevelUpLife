package com.example.LevelUpLife

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.LevelUpLife.databinding.FragmentNewsletterBinding
import com.google.android.material.snackbar.Snackbar


class NewsletterFragment : Fragment() {


    private lateinit var binding: FragmentNewsletterBinding

    //creating my arraylist of emails
    private val emailList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //Making my UndoListner class to remove my email (the last index)
    private inner class UndoListner: View.OnClickListener {
        override fun onClick(v: View?) {
            //removing the lastEmail from the arrayList "emailList"
            emailList.removeAt(emailList.lastIndex)
            Snackbar.make(binding.root,"Your Email has been removed",Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsletterBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        // Get references to the components in the layout
        val btnHome = binding.fabHome
        val btnSignUp = binding.btnSignUp
        val inputEmail = binding.editTextTextEmailAddress


        //onClick to go beck to main
        btnHome.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_newsletterFragment_to_homeFragment)
        }

        //Creating a onClick function for when the signUpbutton is being clicked
        btnSignUp.setOnClickListener(){
            val newEmail = inputEmail.text.toString()

            //Checking if email is not empty and if the email matches the pattern of an email
            if(!newEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()){
                emailList.add(newEmail)

                println(emailList)

                //Making a Snackbar to display message and calling to my UndoListner from my inner class
                Snackbar.make(binding.root,"If you want to undo Signup click Undo", Snackbar.LENGTH_SHORT)
                    .setAction("Undo",UndoListner())
                    .show()

            }

            else {

                println("Your email field is blank")
                Toast.makeText(requireContext(),"You need to enter an Email adress",Toast.LENGTH_LONG).show()
            }


        }


        return view
    }


}