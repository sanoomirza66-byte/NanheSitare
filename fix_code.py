import os

splash_code = '''package com.example.nanhysitary

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val titleText = findViewById<TextView>(R.id.tvNanhySitary)

        val zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        titleText.startAnimation(zoomAnimation)

        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.kid_chime)
            mediaPlayer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
'''

# Find SplashActivity.kt and update it
for root, dirs, files in os.walk("app/src/main/java"):
    for file in files:
        if file == "SplashActivity.kt":
            path = os.path.join(root, file)
            with open(path, "w") as f:
                f.write(splash_code)
            print(f"Updated: {path}")

