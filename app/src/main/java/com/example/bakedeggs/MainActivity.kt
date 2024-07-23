package com.example.bakedeggs

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val serviceLocator=ServiceLocator.getInstance(application)



    }
}