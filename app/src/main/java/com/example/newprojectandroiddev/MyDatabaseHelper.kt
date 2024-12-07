package com.example.newprojectandroiddev

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "user_database.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = """
            CREATE TABLE users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            login TEXT,
            email TEXT,
            password TEXT
            )
        """.trimIndent()

        db?.execSQL(createTable)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(User: SQLite) {
        val values = ContentValues()
        values.put("login", User.login)
        values.put("email", User.email)
        values.put("password", User.password)

        val sqlite = this.writableDatabase
        sqlite.insert("users", null, values)

        sqlite.close()
    }

    fun signIn(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM users WHERE email = '$email' AND password = '$password'",null)
        return result.moveToFirst()
    }

}