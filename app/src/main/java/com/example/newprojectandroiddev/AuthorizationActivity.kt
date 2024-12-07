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

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authorization)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
           val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userEmail = findViewById<EditText>(R.id.editText_EnterEmail_Sign_In)
        val userPassword = findViewById<EditText>(R.id.editText_EnterPassword_Sign_In)
        val signInButton = findViewById<Button>(R.id.Sign_In_button)
        val intentSignUpButton = findViewById<Button>(R.id.Sign_Up_Intent_Button)

        intentSignUpButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            val useremail = userEmail.text.toString().trim()
            val userpassword = userPassword.text.toString().trim()

            if (useremail == "" || userpassword == "") {
                Toast.makeText(this, "Please Enter all fields!", Toast.LENGTH_LONG).show()

            } else {

                val sqlite = MyDatabaseHelper(this)
                val isAuth = sqlite.signIn(useremail, userpassword)
                if (isAuth) {

                    Toast.makeText(this, "You are Sign In Succesful", Toast.LENGTH_LONG).show()
                    userEmail.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, NavigationMainPageAppActivity::class.java)
                    startActivity(intent)
                } else {

                    Toast.makeText(this, "you entered incorrect email or password or you don’t have an account, so register", Toast.LENGTH_LONG).show()
                    userEmail.text.clear()
                    userPassword.text.clear()
                }
            }
        }
}
}
