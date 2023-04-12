package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.LevelUpLife.LevelUp.Users
import com.example.LevelUpLife.databinding.FragmentCreateAccountBinding
import com.example.LevelUpLife.databinding.FragmentUserProfileBinding
import com.google.firebase.database.*

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val userName = binding.tvUserName
        val etChangeUsername = binding.etChangeUserName
        val btnChangeUsername = binding.btnChangeUsername
        val etPassword = binding.etPassword
        val etOldUserName = binding.etOldUserName

        db = FirebaseDatabase
            .getInstance("https://leveluplife-d3204-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("users")


        btnChangeUsername.setOnClickListener{
            val oldUsername = etOldUserName.text.toString()
            val username = etChangeUsername.text.toString()
            val password = etPassword.text.toString()
            db.orderByChild("username").equalTo(oldUsername).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        //Going trough all the index of the children
                        for (userSnapshot in snapshot.children) {
                            val dbUser = userSnapshot.getValue(Users::class.java)

                            if (dbUser != null && dbUser.password == password) {

                                userSnapshot.ref.child("username").setValue(username)

                                    .addOnSuccessListener {
                                        binding.etChangeUserName.text.clear()
                                        Toast.makeText(
                                            requireContext(),
                                            "You successfully changed your username",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }

                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Wrong password or username",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }


                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Your username does not exist, try again",
                            Toast.LENGTH_LONG
                        ).show()
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


        return view
    }

    private fun updateData(username: String) {


    val user = mapOf("username" to username)
        db.child(username).updateChildren(user).addOnSuccessListener {
            binding.etChangeUserName.text.clear()
        }

    }


}

