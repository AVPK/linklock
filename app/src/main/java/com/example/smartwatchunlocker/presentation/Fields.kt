package com.example.smartwatchunlocker.presentation

import com.google.gson.annotations.SerializedName

data class Fields(
    @SerializedName("class")
    val clasName: String? = null,
    val date: String? = null,
    val name: String? = null,
    val time: String? = null
)