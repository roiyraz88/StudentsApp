package com.example.studentsapp.adapter

import Student
import com.example.studentsapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class StudentRecyclerAdapter(private val students: MutableList<Student>) :
    RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

    var listener: OnItemClickListener? = null

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name_text_view)
        private val idTextView: TextView = itemView.findViewById(R.id.student_row_id_text_view)
        private val studentCheckBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)

        fun bind(student: Student, position: Int) {
            nameTextView.text = student.name
            idTextView.text = student.id

            // Avoid triggering onCheckedChange unnecessarily
            studentCheckBox.setOnCheckedChangeListener(null)
            studentCheckBox.isChecked = student.isChecked

            // Update the student object when the checkbox is toggled
            studentCheckBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            // Handle item clicks
            itemView.setOnClickListener {
                listener?.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        // Ensure position is valid before binding
        if (position in students.indices) {
            holder.bind(students[position], position)
        }
    }

    override fun getItemCount(): Int = students.size
}
