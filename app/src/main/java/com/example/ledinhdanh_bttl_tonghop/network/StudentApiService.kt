package com.example.ledinhdanh_bttl_tonghop.network

import retrofit2.http.GET

interface StudentApiService {
    @GET("users")
    suspend fun getStudents(): List<StudentDto>
}