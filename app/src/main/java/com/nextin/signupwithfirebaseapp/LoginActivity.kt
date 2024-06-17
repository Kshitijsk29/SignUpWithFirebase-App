package com.nextin.signupwithfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.nextin.signupwithfirebaseapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding :ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    lateinit var  auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerHere.setOnClickListener {
            startActivity(Intent(this ,SignUpActivity::class.java))
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()|| password.isEmpty()){
                Toast.makeText(this ,"Please fill the details",
                    Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    task ->
                    if (task.isSuccessful)
                    {
                        Toast.makeText(this,
                            "Login is Successful",
                            Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,
                            "Login failed ${task.exception?.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                    .addOnFailureListener{
                        task ->
                        Toast.makeText(this,
                            "The Error is : ${task.message}"
                        ,Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}