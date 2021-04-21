package com.example.evonza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_account.*

class Account : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        afullnameEdit.visibility = View.GONE
        acityEdit.visibility = View.GONE
        submit.visibility = View.GONE
        val firebase = FirebaseFirestore.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseRealTime = FirebaseDatabase.getInstance()
        val userProfileData = firebaseRealTime.reference.child("profile").child((firebaseAuth.currentUser.uid))
        navigationView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.dashboard -> {
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                    }

                    R.id.addservice -> {
                        val intent = Intent(this, AddService::class.java)
                        startActivity(intent)
                    }

                    R.id.account -> {
                        val intent = Intent(this, Account::class.java)
                        startActivity(intent)
                    }
                }
                return@OnNavigationItemSelectedListener true
            })
        logoutbutton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

//        userProfileData.child("Full Name").value

        editbutton.setOnClickListener {
            editbutton.visibility = View.GONE
            afullnameEdit.setText(afullname.text)
            acityEdit.setText(acity.text)
            acityEdit.visibility = View.VISIBLE
            firebase.collection("users")
            afullnameEdit.visibility = View.VISIBLE
            submit.visibility = View.VISIBLE
            afullname.visibility = View.GONE
            acity.visibility = View.GONE

        }

        submit.setOnClickListener {
            editbutton.visibility = View.VISIBLE
            submit.visibility = View.GONE
            afullname.text = afullnameEdit.text
            acity.text = acityEdit.text
            userProfileData.child("Full Name").setValue(afullname.text.toString())
            userProfileData.child("City").setValue(acity.text.toString())

            afullname.visibility = View.VISIBLE
            acity.visibility = View.VISIBLE
            afullnameEdit.visibility = View.GONE
            acityEdit.visibility = View.GONE
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        loadAccount()
    }

    private fun loadAccount(){
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        aemail.text = user?.email

        userreference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                afullname.text = snapshot.child("Full Name").value.toString()
                amobilenumber.text = snapshot.child("Mobile Number").value.toString()
                acity.text = snapshot.child("City").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        logoutbutton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@Account, MainActivity::class.java))
            finish()
        }
    }
}