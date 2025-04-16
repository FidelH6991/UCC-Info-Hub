
package com.example.majproject.model

data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val credits: Int,
    val prerequisites: String,
    val description: String
)
