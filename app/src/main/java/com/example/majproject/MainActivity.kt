package com.example.majproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDirectory = findViewById<LinearLayout>(R.id.btn_directory)
        val btnCourses = findViewById<LinearLayout>(R.id.btn_courses)
        val btnAdmissions = findViewById<LinearLayout>(R.id.btn_admissions)
        val btnSocial = findViewById<LinearLayout>(R.id.btn_social)
        val fabEmail = findViewById<FloatingActionButton>(R.id.fab_email)

        btnDirectory.setOnClickListener {
            startActivity(Intent(this, DirectoryActivity::class.java))
        }

        btnCourses.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }

        btnAdmissions.setOnClickListener {
            startActivity(Intent(this, AdmissionsActivity::class.java))
        }

        btnSocial.setOnClickListener {
            startActivity(Intent(this, SocialMediaActivity::class.java))
        }

        fabEmail.setOnClickListener {
            sendFacultyEmail()
        }
    }

    private fun sendFacultyEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:itfaculty@ucc.edu.jm")
            putExtra(Intent.EXTRA_SUBJECT, "Inquiry from STEAM Faculty App")
        }

        try {
            startActivity(Intent.createChooser(intent, "Send Email"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No email app found on this device.", Toast.LENGTH_LONG).show()
        }
    }
}
