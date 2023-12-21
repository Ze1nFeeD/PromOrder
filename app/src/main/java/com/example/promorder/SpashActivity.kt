package com.example.promorder

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class SpashActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)
        supportActionBar?.hide()
        val imageView: ImageView = findViewById(R.id.imageView)
        val textView: TextView = findViewById(R.id.textView)
        val shimmerLayout: ShimmerFrameLayout = findViewById(R.id.shimmerLayout)
        imageView.alpha = 0f
        textView.alpha = 0f
        shimmerLayout.startShimmer()

        // Анимация появления ImageView
        imageView.animate().alpha(1f).setDuration(1500).withEndAction {
            // Анимация появления TextView
            textView.animate().alpha(1f).setDuration(1500).withEndAction {
                textView.visibility = View.VISIBLE
                // Задержка перед запуском MainActivity
                handler.postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 4000)
            }
        }
    }

    }
