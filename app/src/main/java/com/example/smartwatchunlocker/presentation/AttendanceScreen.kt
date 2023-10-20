package com.example.smartwatchunlocker.presentation

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.smartwatchunlocker.R
import com.example.smartwatchunlocker.utils.Validator
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


class AttendanceScreen : AppCompatActivity() {

    private val adapterList by lazy {
        AttendanceListAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_screen)
        val localTime = LocalTime.now()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm")
        findViewById<TextView>(R.id.time_tv).text = localTime.format(dateTimeFormatter)

        getAttendance()

    }

    private fun getAttendance() {
        lifecycleScope.launchWhenCreated {
//            try {
                val getResp = RetrofitClient.service.getAttendance()
                if (getResp.isSuccessful) {

                    val responseData = getResp.body()
                    if (responseData != null) {
                        adapterList.submitList(responseData.records.reversed())
                        findViewById<RecyclerView>(R.id.report_content_rv).adapter = adapterList.apply {
                            onClickReportContainer = { position, listData ->
                                Toast.makeText(this@AttendanceScreen, "${listData.fields.name}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Log.d("ffnet", "addAttendance: Response data is null")
                    }
                } else {
                    Log.d("ffnet", "addAttendance: get attendance err: ${getResp.errorBody().toString()} ")

                }
//            } catch (Ex: Exception) {
//                Log.e("Error", Ex.localizedMessage)
//            }
        }

    }

}