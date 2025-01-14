package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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

        class Listener : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(this@MainActivity, AddStudentActivity::class.java)
                startActivity(intent)
            }

        }

        val addStudentButton: Button = findViewById(R.id.main_activity_add_student_button)
        val listener = Listener()
        addStudentButton.setOnClickListener(listener)
    }
}