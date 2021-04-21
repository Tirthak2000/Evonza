package com.example.evonza

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_party_plot.*

class PartyPlot : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_plot)

        val firebaseFirestore = FirebaseFirestore.getInstance()
        val type = intent.getStringExtra("name")
//        firebaseFirestore.collection("")

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()
        val currUserRef = firebaseDatabase.getReference("profile").child("${firebaseAuth.currentUser!!.uid}").child("City")

        var userCity = ""

        currUserRef.get().addOnCompleteListener {
            Log.d("Party", it.result!!.value.toString())
            userCity = it.result!!.value.toString()

            firebaseFirestore.collection("businesses")
                    .whereEqualTo("city", "${userCity}")
                    .whereEqualTo("businessType", "${type}")
                    .orderBy("minPrice")
                    .get()

                    .addOnSuccessListener {

                        val allData = it.documents

//                Log.d("PartyPlot", it.documents[0].toString())
                        val adapter = BusinessListAdapter(this, allData, this)
                        businessRecycler.layoutManager = LinearLayoutManager(this)
                        businessRecycler.adapter = adapter
//                Log.d("PartyPlot", it.data.toString())
                    }
                    .addOnFailureListener {
                        Log.e("Party Plot", it.localizedMessage);
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }

        }


    }

    override fun onCellClickListener(name: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "email@email.com", null))
        intent.putExtra(Intent.EXTRA_SUBJECT, "something")
        intent.putExtra(Intent.EXTRA_TEXT, android.R.id.message)
        startActivity(Intent.createChooser(intent, "Choose an Email client :"))
    }
}