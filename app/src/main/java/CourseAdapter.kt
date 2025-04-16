package com.example.majproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.majproject.R
import com.example.majproject.model.Course

class CourseAdapter(private val courses: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.courseTitle)
        val code: TextView = itemView.findViewById(R.id.courseCode)
        val credits: TextView = itemView.findViewById(R.id.courseCredits)
        val prereq: TextView = itemView.findViewById(R.id.coursePrereq)
        val desc: TextView = itemView.findViewById(R.id.courseDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.title.text = course.name
        holder.code.text = "Code: ${course.code}"
        holder.credits.text = "Credits: ${course.credits}"
        holder.prereq.text = "Prerequisites: ${course.prerequisites}"
        holder.desc.text = "Description: ${course.description}"
    }

    override fun getItemCount(): Int = courses.size
}
