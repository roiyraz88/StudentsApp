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

        fun bind(student: Student) {
            nameTextView.text = student.name
            idTextView.text = student.id
            studentCheckBox.isChecked = student.isChecked

            studentCheckBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size
}
