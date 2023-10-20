package com.example.smartwatchunlocker.presentation

import com.google.gson.annotations.SerializedName

data class Record(
    val id: String,
    val createdTime: String,
    val fields: RecordFields
)

data class RecordFields(
    @SerializedName("class")
    val classField: String?, // Note: Use nullable type if the field can be missing
    val time: String?,
    val date: String?,
    val name: String?,
    val createdat: String
)

data class ResponseData(
    val records: List<Record>
)
