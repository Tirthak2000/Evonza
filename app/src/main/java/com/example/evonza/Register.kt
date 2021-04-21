package com.example.evonza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register() {
        registerbutton.setOnClickListener {

            if (TextUtils.isEmpty(rfullname.text.toString())){
                rfullname.setError("Please Enter Full Name")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(rmobilenumber.text.toString())){
                rmobilenumber.setError("Please Enter Mobile Number")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(rcity.text.toString())){
                rcity.setError("Please Enter City")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(remail.text.toString())){
                remail.setError("Please Enter Email Address")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(rpassword.text.toString())){
                rpassword.setError("Please Enter Password")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(rconfirmpassword.text.toString())){
                rconfirmpassword.setError("Please Enter Confirm Password")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(remail.text.toString(), rpassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val currentUser = auth.currentUser
                            val currentUserDB = databaseReference?.child((currentUser?.uid!!))
                            currentUserDB?.child("Full Name")?.setValue(rfullname.text.toString())
                            currentUserDB?.child("Mobile Number")?.setValue(rmobilenumber.text.toString())
                            currentUserDB?.child("City")?.setValue(rcity.text.toString())
                            //currentUserDB?.child("Email Address")?.setValue(rfullname.text.toString())
                            //currentUserDB?.child("Password")?.setValue(rfullname.text.toString())
                            //currentUserDB?.child("Confirm Password")?.setValue(rfullname.text.toString())

                            Toast.makeText(this@Register, "Registration Successful!", Toast.LENGTH_LONG).show()
                            finish()

                        } else {
                            Toast.makeText(this@Register, "Registration failed, please try again!", Toast.LENGTH_LONG).show()
                        }
                    }
        }
        logintext.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}