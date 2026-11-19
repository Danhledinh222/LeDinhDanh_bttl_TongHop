package com.example.ledinhdanh_bttl_tonghop.network

import com.example.ledinhdanh_bttl_tonghop.model.Student

data class StudentDto(
    val id: Int = 0,
    val name: String = "",
    val email: String = ""
)

fun StudentDto.toEntity(): Student {
    return Student(
        id = 0,
        fullName = this.name,
        studentCode = "API2026${this.id}",
        email = this.email,
        gpa = 3.50,
        major = "Công Nghệ Thông Tin"
    )
}