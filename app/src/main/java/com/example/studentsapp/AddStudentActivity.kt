package com.example.studentsapp

import Student
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_activity_cancel_button)

        val nameEditText: EditText = findViewById(R.id.add_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.add_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.add_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.add_student_activity_address_edit_text)
        val isCheckedCheckBox: CheckBox = findViewById(R.id.add_student_activity_checked_checkbox)

        val saveMessageTextView: TextView = findViewById(R.id.add_student_activity_save_message_text_view)

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val isChecked = isCheckedCheckBox.isChecked

            if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                saveMessageTextView.text = "Please fill in all fields"
            } else {
                val newStudent = Student(
                    name = name,
                    id = id,
                    avatarUrl = "",
                    phone = phone,
                    address = address,
                    isChecked = isChecked
                )

                Model.shared.students.add(newStudent)

                val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
                startActivity(intent)

                finish()
            }
        }
    }
}
