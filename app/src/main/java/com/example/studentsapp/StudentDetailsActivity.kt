package com.example.studentsapp

import Student
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var avatarImageView: ImageView
    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        nameTextView = findViewById(R.id.student_details_name_text_view)
        idTextView = findViewById(R.id.student_details_id_text_view)
        avatarImageView = findViewById(R.id.student_avatar)
        editButton = findViewById(R.id.edit_student_button)

        val student = intent.getSerializableExtra("student") as? Student

        nameTextView.text = student?.name ?: "No name"
        idTextView.text = student?.id ?: "No ID"

        avatarImageView.setImageResource(R.drawable.man)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student", student)
            startActivity(intent)
        }
    }
}
