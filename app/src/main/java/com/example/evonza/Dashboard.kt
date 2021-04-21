package com.example.evonza

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.view.animation.Animation
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    lateinit var logoAnimation1: AnimationDrawable
    lateinit var logoAnimation2: AnimationDrawable
    lateinit var animation1: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        heartIcon.setBackgroundResource(R.drawable.heart_animation)

        logoAnimation1 = heartIcon.background as AnimationDrawable

        dashPhotos.setBackgroundResource(R.drawable.dash_image_animation)
        logoAnimation2 = dashPhotos.background as AnimationDrawable

        cardpartyPlot.setOnClickListener {

            Intent(this, PartyPlot::class.java).putExtra("name", "Party Plot")

                .also {
                    startActivity(it)
                }
        }

        cardHotel.setOnClickListener {

            Intent(this, PartyPlot::class.java).putExtra("name", "Hotel")

                    .also {
                        startActivity(it)
                    }
        }


        cardWedding.setOnClickListener {

            Intent(this, PartyPlot::class.java).putExtra("name", "Wedding Decoration")

                    .also {
                        startActivity(it)
                    }
        }

        cardPartyDecoration.setOnClickListener {

            Intent(this, PartyPlot::class.java).putExtra("name", "Party Decoration")

                    .also {
                        startActivity(it)
                    }
        }

        cardFood.setOnClickListener {

            Intent(this, PartyPlot::class.java).putExtra("name", "Food/Catering")

                    .also {
                        startActivity(it)
                    }
        }



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
    }
}