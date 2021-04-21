package com.example.evonza

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        login()

        if(auth.currentUser !=null){
            Intent(this, Dashboard::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun login(){
        loginbutton.setOnClickListener {
            if (TextUtils.isEmpty(lemail.text.toString())){
                lemail.setError("Please Enter Email Address")
                return@setOnClickListener
            } else if(TextUtils.isEmpty(lpassword.text.toString())){
                lpassword.setError("Please Enter Password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(lemail.text.toString(), lpassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this@MainActivity, Dashboard::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Login failed, please try again!", Toast.LENGTH_LONG).show()
                    }

                }
        }

        lforgotpassword.setOnClickListener {
            Intent(this, Forgotpassword::class.java).also {
                startActivity(it)
            }
        }

        registertext.setOnClickListener {
            Intent(this, Register::class.java).also {
                startActivity(it)
            }
        }
    }
}