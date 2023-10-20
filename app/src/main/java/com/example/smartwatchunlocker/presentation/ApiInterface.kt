package com.example.smartwatchunlocker.presentation

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("/v0/appnkF9ROSM8HEAYu/tbl2akbdhXFmmdgzQ")
    suspend fun addAttendance(@Body attendanceDataClass: AttendanceDataClass): Response<AttendanceDataClass>

    @GET("/v0/appnkF9ROSM8HEAYu/tbl2akbdhXFmmdgzQ")
    suspend fun getAttendance(): Response<ResponseData>
}