package com.example.studentsapp

import Student
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var avatarImageView: ImageView
    private lateinit var editButton: Button
    private var student: Student? = null

    private val editStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val updatedStudent = result.data?.getSerializableExtra("updatedStudent") as? Student
                updatedStudent?.let {
                    // Update the local student reference
                    student = it

                    // Update the shared list
                    val index = Model.shared.students.indexOfFirst { student -> student.id == it.id }
                    if (index != -1) {
                        Model.shared.students[index] = it
                    }

                    // Update UI
                    updateUI()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Bind views
        nameTextView = findViewById(R.id.student_details_name_text_view)
        idTextView = findViewById(R.id.student_details_id_text_view)
        phoneTextView = findViewById(R.id.student_details_phone_text_view)
        addressTextView = findViewById(R.id.student_details_address_text_view)
        avatarImageView = findViewById(R.id.student_avatar)
        editButton = findViewById(R.id.edit_student_button)

        // Get student from intent
        student = intent.getSerializableExtra("student") as? Student
        updateUI()

        // Edit button logic
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student", student)
            editStudentLauncher.launch(intent)
        }
    }

    private fun updateUI() {
        student?.let {
            nameTextView.text = it.name
            idTextView.text = it.id
            phoneTextView.text = it.phone
            addressTextView.text = it.address
            avatarImageView.setImageResource(R.drawable.man) // Placeholder avatar
        }
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("updatedStudent", student)
        setResult(RESULT_OK, resultIntent)
        super.onBackPressed()
    }
}
