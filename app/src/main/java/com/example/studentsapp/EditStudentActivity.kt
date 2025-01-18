package com.example.studentsapp

import Student
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class EditStudentActivity : AppCompatActivity() {

    private lateinit var imageViewProfile: ImageView
    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var checkBoxIsChecked: CheckBox
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button
    private lateinit var buttonDelete: Button
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Bind views
        imageViewProfile = findViewById(R.id.imageViewProfile)
        editTextName = findViewById(R.id.editTextName)
        editTextId = findViewById(R.id.editTextId)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextAddress = findViewById(R.id.editTextAddress)
        checkBoxIsChecked = findViewById(R.id.checkBoxIsChecked)
        buttonCancel = findViewById(R.id.buttonCancel)
        buttonSave = findViewById(R.id.buttonSave)
        buttonDelete = findViewById(R.id.buttonDelete)

        // Get student from intent
        student = intent.getSerializableExtra("student") as? Student
        student?.let {
            editTextName.setText(it.name)
            editTextId.setText(it.id)
            editTextPhone.setText(it.phone)
            editTextAddress.setText(it.address)
            checkBoxIsChecked.isChecked = it.isChecked
        }

        // Save button logic
        buttonSave.setOnClickListener {
            student?.apply {
                name = editTextName.text.toString()
                id = editTextId.text.toString()
                phone = editTextPhone.text.toString()
                address = editTextAddress.text.toString()
                isChecked = checkBoxIsChecked.isChecked

                // Update shared list
                val index = Model.shared.students.indexOfFirst { it.id == this.id }
                if (index != -1) {
                    Model.shared.students[index] = this
                }
            }

            val resultIntent = Intent()
            resultIntent.putExtra("updatedStudent", student)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // Cancel button logic
        buttonCancel.setOnClickListener {
            finish()
        }

        // Delete button logic
        buttonDelete.setOnClickListener {
            student?.let {
                val index = Model.shared.students.indexOfFirst { it.id == student?.id }
                if (index != -1) {
                    // Remove student from shared list
                    Model.shared.students.removeAt(index)
                }

                // Return directly to StudentsRecyclerViewActivity
                val resultIntent = Intent()
                resultIntent.putExtra("deletedStudentId", student?.id)
                setResult(RESULT_OK, resultIntent)
                finish() // Close EditStudentActivity
            }
        }
    }
}
