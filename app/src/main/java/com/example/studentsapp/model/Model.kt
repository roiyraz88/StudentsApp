package com.example.studentsapp.model

import Student

class Model private constructor(){

    val students: MutableList<Student> = ArrayList()

    companion object{
        val shared = Model()
    }

    init {
            val student = Student(
                name = "John Doe",
                id = "123456",
                avatarUrl = "",
                isChecked = false
            )
            students.add(student)
        }
    }
