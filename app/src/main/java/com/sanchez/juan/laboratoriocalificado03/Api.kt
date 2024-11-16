package com.sanchez.juan.laboratoriocalificado03

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("list/teacher")
    fun getTeachers(): Call<Map<String, List<Teacher>>>
}