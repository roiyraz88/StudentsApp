package com.example.studentsapp

import Student
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.OnItemClickListener
import com.example.studentsapp.adapter.StudentRecyclerAdapter
import com.example.studentsapp.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsRecyclerViewActivity : AppCompatActivity() {

    private lateinit var students: MutableList<Student>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerAdapter

    private val studentDetailsLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val updatedStudent = result.data?.getSerializableExtra("updatedStudent") as? Student
                val deletedStudentId = result.data?.getStringExtra("deletedStudentId")

                updatedStudent?.let {
                    val index = students.indexOfFirst { student -> student.id == it.id }
                    if (index != -1) {
                        Model.shared.students[index] = it
                        students[index] = it
                        adapter.notifyItemChanged(index) // Update the RecyclerView
                    }
                }

                deletedStudentId?.let {
                    val index = students.indexOfFirst { student -> student.id == it }
                    if (index != -1) {
                        Model.shared.students.removeAt(index)
                        students.removeAt(index)
                        adapter.notifyItemRemoved(index)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_recycler_view)

        students = Model.shared.students

        recyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerAdapter(students)
        recyclerView.adapter = adapter

        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedStudent = students[position]
                val intent = Intent(this@StudentsRecyclerViewActivity, StudentDetailsActivity::class.java)
                intent.putExtra("student", selectedStudent)
                studentDetailsLauncher.launch(intent)
            }
        }

        val fab: FloatingActionButton = findViewById(R.id.add_student_fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}
