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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nextin.signupwithfirebaseapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding :ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    companion object{
        const val  KEY1 = "com.nextin.signupwithfirebaseapp.LoginActivity.name"
        const val  KEY2 = "com.nextin.signupwithfirebaseapp.LoginActivity.email"
    }

     lateinit var database : DatabaseReference
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
                        val id = task.result.user?.uid
                        if (id!= null){
                            readTheDataFromFirebase(id!!)
                        }

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

    private fun readTheDataFromFirebase(id: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(id).get().addOnSuccessListener {
            if(it.exists()){
                val name = it.child("uname").value
                val email = it.child("email").value

                val welcomeIntent = Intent(this,MainActivity::class.java)
                welcomeIntent.putExtra(KEY1,name.toString())
                welcomeIntent.putExtra(KEY2,email.toString())
                startActivity(welcomeIntent)
            }else
            {
                Toast.makeText(this,"User Does Not Exits"
                    ,Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,
                    "Error : - ${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }
    }
}