package com.example.ledinhdanh_bttl_tonghop.repository

import com.example.ledinhdanh_bttl_tonghop.data.StudentDao
import com.example.ledinhdanh_bttl_tonghop.model.Student
import com.example.ledinhdanh_bttl_tonghop.network.RetrofitClient
import com.example.ledinhdanh_bttl_tonghop.network.StudentApiService
import com.example.ledinhdanh_bttl_tonghop.network.toEntity
import kotlinx.coroutines.flow.Flow

class StudentRepository(
    private val studentDao: StudentDao,
    private val apiService: StudentApiService = RetrofitClient.apiService
) {
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student): Long = studentDao.insertStudent(student)

    suspend fun delete(student: Student) = studentDao.deleteStudent(student)

    suspend fun syncFromCloud(): Result<Int> {
        return try {
            val remoteList = apiService.getStudents()
            val entities = remoteList.map { it.toEntity() }
            studentDao.insertAll(entities)
            Result.success(entities.size)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}