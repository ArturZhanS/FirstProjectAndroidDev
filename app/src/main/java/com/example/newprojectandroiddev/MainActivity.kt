package com.example.newprojectandroiddev

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lateinit var dbHelper: MyDatabaseHelper

        val userLogin = findViewById<EditText>(R.id.editText_EnterLogin)
        val userEmail = findViewById<EditText>(R.id.editText_EnterEmail_Sign_Up)
        val userPassword = findViewById<EditText>(R.id.editText_EnterPassword_Sign_Up)
        val signUpButton = findViewById<Button>(R.id.Sign_Up_button)
        val intentSignInButton = findViewById<Button>(R.id.Sign_In_Intent_Button)

        intentSignInButton.setOnClickListener {
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            val userlogin = userLogin.text.toString().trim()
            val useremail = userEmail.text.toString().trim()
            val userpassword = userPassword.text.toString().trim()

            if (userlogin == "" || useremail == "" || userpassword == ""){
                Toast.makeText(this, "Please Enter all fields!", Toast.LENGTH_LONG).show()
            } else {
                val user = SQLite(userlogin, useremail, userpassword)
                val sqlite = MyDatabaseHelper(this)
                sqlite.addUser(user)

                Toast.makeText(this, "You are Create Account Succesful", Toast.LENGTH_LONG).show()
                userLogin.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()

                val intent = Intent(this, NavigationMainPageAppActivity::class.java)
                startActivity(intent)

            }
        }
    }
}