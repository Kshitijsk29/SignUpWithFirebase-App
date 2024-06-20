package com.nextin.signupwithfirebaseapp

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.nextin.signupwithfirebaseapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra(LoginActivity.KEY1)
        val email = intent.getStringExtra(LoginActivity.KEY2)

       binding.name.text = "Welcome ${name}"
        binding.email.text= "This is your ${email}"
    }
}