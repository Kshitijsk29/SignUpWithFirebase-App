package com.nextin.signupwithfirebaseapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nextin.signupwithfirebaseapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding :ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerHere.setOnClickListener {
            startActivity(Intent(this ,SignUpActivity::class.java))
        }
    }
}