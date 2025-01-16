package com.example.studentsapp.model

import Student

class Model private constructor() {

    val students: MutableList<Student> = mutableListOf()

    companion object {
        val shared: Model by lazy { Model() }
    }

    init {
        // Sample data for testing
        val sampleStudent = Student(
            name = "John Doe",
            id = "123456",
            phone = "123-456-7890",
            address = "123 Main St",
            avatarUrl = "",
            isChecked = false
        )
        students.add(sampleStudent)
    }
}
