package com.example.LevelUpLife

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.UserViewModel
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentUserProfileBinding
import com.google.firebase.database.*

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    private lateinit var db: DatabaseReference

    //initialize a relation bound viewModel with activityViewModel
    private val viewModel : UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val etChangeUsername = binding.etChangeUserName
        val btnChangeUsername = binding.btnChangeUsername
        val btnDeleteUser = binding.btnDeleteUser
        val tvUserName = binding.tvUserName
        val btnLoggedInHomeFragment = binding.fabHome


        btnLoggedInHomeFragment.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_loggedInHomeFragment)
        }



        //Fetching my db connection
        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")


        //Fetching child from the database
        db.orderByChild("email")
                //checking if email is the same as in my UiState
            .equalTo(viewModel.uiState.value.email)
            .addListenerForSingleValueEvent(object : ValueEventListener   {
                override fun onDataChange(datasnapshot: DataSnapshot) {
                    if (datasnapshot.exists()){
                     for (i in datasnapshot.children) {
                         //creating a variabel for the user from my database
                         val dbUser = i.getValue(Users::class.java)
                         //creating a variable for my username from my database
                         val username = dbUser?.username

                        //setting the username to
                         tvUserName.text = username
                    }
                     }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        "Failure: something went wrong with the database connection",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } )



        btnChangeUsername.setOnClickListener {

            val username = etChangeUsername.text.toString()

            // Check if the username EditText is empty
            if (username.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Please enter a username to be able to change it",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }


            db.orderByChild("email").equalTo(viewModel.uiState.value.email).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        //Going trough all the index of the children
                        for (userSnapshot in snapshot.children) {
                            val dbUser = userSnapshot.getValue(Users::class.java)

                            if (dbUser != null ) {

                                userSnapshot.ref.child("username").setValue(username)

                                    .addOnSuccessListener {
                                        binding.etChangeUserName.text.clear()
                                         binding.tvUserName.text = username
                                        Toast.makeText(
                                            requireContext(),
                                            "You successfully changed your username",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }

                            }
                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        "Failure: something went wrong with the database connection",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })

        }


        btnDeleteUser.setOnClickListener {

            //creating an alert dialog if the user want so delete their profile
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Delete profile")
            alert.setMessage("Are you sure you want to delete your profile?")

            alert.setPositiveButton("Yes") {_, _ ->

                db.orderByChild("email").equalTo(viewModel.uiState.value.email)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {

                                for (userSnapshot in snapshot.children) {
                                    userSnapshot.ref.removeValue().addOnSuccessListener {
                                        Toast.makeText(
                                            requireContext(),
                                            "You successfully deleted your profile",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                        //If the user is deleted navigate to the home fragment
                                        Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_homeFragment)

                                    }
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(
                                requireContext(),
                                "Failure: something went wrong with the database connection: $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })


            }
            alert.setNegativeButton("No") { _, _ -> }
            val dialog = alert.create()
            dialog.show()


        }


        return view
    }
}



