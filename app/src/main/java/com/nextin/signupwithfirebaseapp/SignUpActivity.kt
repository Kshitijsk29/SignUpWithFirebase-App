package com.nextin.signupwithfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.nextin.signupwithfirebaseapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

     private val binding: ActivitySignUpBinding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.alreadyHaveAccount.setOnClickListener {
            startActivity(Intent(this ,LoginActivity::class.java))

        }

        binding.btnSignUp.setOnClickListener {
            val username = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val repeatedPassword = binding.etConfirmPassword.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()||repeatedPassword.isEmpty())
                {
                    Toast.makeText(this,
                        "Please fill the Sign Up Details", Toast.LENGTH_SHORT).show()
                }
                else if(repeatedPassword != password){
                    Toast.makeText(this,
                        "Make Sure Confirm password Matched with password",
                        Toast.LENGTH_SHORT).show()
                }
            else
            {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Registration Successful ",
                                Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                        else
                        {
                            Toast.makeText(this,"Registration Failed ${task.exception?.message} ",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}