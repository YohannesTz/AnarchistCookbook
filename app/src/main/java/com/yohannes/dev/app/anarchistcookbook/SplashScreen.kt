package com.yohannes.dev.app.anarchistcookbook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        try {
            Thread.sleep(2500)
            goToActivity()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun goToActivity() {
        val mainActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivity)
    }
}