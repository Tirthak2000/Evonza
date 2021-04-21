package com.example.eventblending

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.evonza.MainActivity
import com.example.evonza.R
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity(), Animation.AnimationListener {
    lateinit var logoAnimation: AnimationDrawable
    lateinit var animation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        logo.setBackgroundResource(R.drawable.logo_animation)

        logoAnimation = logo.background as AnimationDrawable

        animation = AnimationUtils.loadAnimation(this, R.anim.grow_rotate)
        animation.setAnimationListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
            logoAnimation.start()
            logo.startAnimation(animation)
        } else {
            logoAnimation.stop()
        }
    }

    override fun onAnimationStart(animation: Animation?) {
        //
    }

    override fun onAnimationEnd(p0: Animation?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
        finish()
    }

    override fun onAnimationRepeat(animation: Animation?) {
        //
    }
}