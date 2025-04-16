package com.example.majproject.database


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.majproject.model.Course


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "CoursesDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE courses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                code TEXT,
                name TEXT,
                credits INTEGER,
                prerequisites TEXT,
                description TEXT
            )
            """.trimIndent()
        )


        val courses = listOf(
            Course(
                0,
                "SCI101",
                "Intro to Physical Science",
                3,
                "None",
                "Basics of physics, chemistry, and earth science."
            ),
            Course(
                0,
                "TEC201",
                "Fundamentals of Technology",
                4,
                "SCI101",
                "Explores emerging tech and basic engineering."
            ),
            Course(
                0,
                "ENG110",
                "Engineering Drawing",
                3,
                "None",
                "Covers CAD and technical design communication."
            ),
            Course(
                0,
                "ART105",
                "Digital Media & Design",
                3,
                "None",
                "Combines art principles with digital tools."
            ),
            Course(
                0,
                "MAT120",
                "Calculus I",
                4,
                "None",
                "Differentiation and integration of functions."
            ),
            Course(0, "MAT121", "Calculus II", 4, "MAT120", "Advanced integration and series."),
            Course(
                0,
                "BIO140",
                "Intro to Biology",
                3,
                "None",
                "Cell structure, genetics, and ecology."
            ),
            Course(
                0,
                "CHE110",
                "General Chemistry",
                4,
                "SCI101",
                "Chemical reactions, stoichiometry, periodic trends."
            ),
            Course(
                0,
                "PHY130",
                "General Physics",
                4,
                "MAT120",
                "Kinematics, dynamics, and energy systems."
            ),
            Course(
                0,
                "STE499",
                "Capstone Project",
                3,
                "TEC201, MAT121",
                "Multidisciplinary project applying STEAM skills."
            )
        )

        for (course in courses) {
            db.execSQL(
                "INSERT INTO courses (code, name, credits, prerequisites, description) VALUES (?, ?, ?, ?, ?)",
                arrayOf(course.code, course.name, course.credits, course.prerequisites, course.description)
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS courses")
        onCreate(db)
    }

    fun getAllCourses(): List<Course> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM courses", null)
        val courses = mutableListOf<Course>()

        if (cursor.moveToFirst()) {
            do {
                courses.add(
                    Course(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("code")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("credits")),
                        cursor.getString(cursor.getColumnIndexOrThrow("prerequisites")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description"))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return courses
    }
}
