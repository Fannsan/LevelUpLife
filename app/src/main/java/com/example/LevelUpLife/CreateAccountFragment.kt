package com.example.LevelUpLife

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentCreateAccountBinding
import com.google.firebase.database.*


class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    private lateinit var db: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentCreateAccountBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val etEmail = binding.etEmailAdress
        val etCreatePassword = binding.etNewPassword
        val etConfirmPassword = binding.etConfirmPassword
        val btnCreateAcount = binding.btnCreateAccount
        val fabHome = binding.fabHome

        var listOfUsers = arrayListOf<Users>()


        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")

        //Navigate to Home
        fabHome.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
        }


        btnCreateAcount.setOnClickListener{

            val email = etEmail.text.toString()
            val password = etCreatePassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            val query = db.child("users")
                .orderByChild("email").equalTo(email)
            query.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    when {
                        snapshot.exists() ->{
                        Toast.makeText(requireContext(),"Email already exists", Toast.LENGTH_LONG).show()
                    }
                    //checking if the user has written email and passwords in the fields
                    email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->{
                        Toast.makeText(requireContext(),"You need to enter a valid email address and passwords",Toast.LENGTH_LONG).show()

                    //Check if password and confirm password is not the same
                        } password != confirmPassword -> {


                    }else -> {

                        //Creating a new User
                        val newUser = Users(email, password, isRegistred = true)

                        //push the new user to the database
                        db.push()
                            .setValue(newUser)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    requireContext(),
                                    "You made a new account!",
                                    Toast.LENGTH_LONG
                                ).show()

                                //getting rid of text I have in my textfields
                                binding.etEmailAdress.text.clear()
                                binding.etNewPassword.text.clear()
                                binding.etConfirmPassword.text.clear()

                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    "Failure: something went wrong $it",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                    }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            )




            /*
           db.child("users")
               .orderByChild("username") //Sorting in the database by "username"
               .equalTo(email) //matching the username from the database with the input from the user
               .get()
               .addOnSuccessListener {

                   if (it.exists()){
                       Toast.makeText(requireContext(),"The username you entered already exist try another", Toast.LENGTH_LONG).show()
                       println("the username already exist")
                   }
*/


               // .addOnFailureListener{
                //    Toast.makeText(requireContext(),"Failure: something went wrong", Toast.LENGTH_LONG).show()
        //    println("något gick fel ") }

        }



        return view
    }


}