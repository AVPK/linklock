package com.example.smartwatchunlocker.presentation

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.example.smartwatchunlocker.R
import com.example.smartwatchunlocker.utils.Validator
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


class AttendanceMarkScreen : AppCompatActivity() {
    companion object {
        var donneMarked: Boolean = false
    }

    var nameValid = false
    var dateValid = false
    var classValid = false
    var arriveValid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_mark)

        val localTime = LocalTime.now()
//        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm")
        findViewById<TextView>(R.id.time_tv).text = localTime.format(dateTimeFormatter)
        validateInputFields1()

//        if (dateValid && nameValid && classValid && arriveValid){
//            findViewById<CardView>(R.id.login_btn).isEnabled = true
//            findViewById<CardView>(R.id.login_btn).setCardBackgroundColor(Color.parseColor("#FF0F9CC1"))
//        }else{
//            findViewById<CardView>(R.id.login_btn).isEnabled = false
//            findViewById<CardView>(R.id.login_btn).setCardBackgroundColor(Color.parseColor("#FF31353E"))
//        }
        findViewById<CardView>(R.id.login_btn).setOnClickListener {

            donneMarked = true
            Toast.makeText(this, "Done submission", Toast.LENGTH_SHORT).show()
//            findViewById<ConstraintLayout>(R.id.email_container).setBackgroundResource(R.drawable.name_pwd_bg)
            super.onBackPressed()


        }
        findViewById<TextView>(R.id.password_et).setOnClickListener {
            showDatePicker()
        }


    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.CustomDatePickerDialogTheme,
            onDateSetListener,
            calendar.get(Calendar.YEAR) - 13,
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.descendantFocusability = DatePicker.FOCUS_BLOCK_DESCENDANTS
        datePickerDialog.setTitle("Please select date")
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        val day = String.format("%02d", dayOfMonth)
        val monthNumber = String.format("%02d", (month + 1))
        findViewById<TextView>(R.id.password_et).text = "$monthNumber/$day/$year"
    }

    private fun validateInputFields1() {
        val emailEditText = findViewById<EditText>(R.id.email_tv)
        val dateEditText = findViewById<TextView>(R.id.password_et)
        val classEditText = findViewById<EditText>(R.id.class_et)
        val arriveEditText = findViewById<EditText>(R.id.arrive_et)

        emailEditText.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                nameValid = false
                findViewById<ConstraintLayout>(R.id.email_container).setBackgroundResource(R.drawable.name_pwd_bg)
            } else if (!Validator.isSimpleNameValid(it.toString())) {
                nameValid = false
                findViewById<ConstraintLayout>(R.id.email_container).setBackgroundResource(R.drawable.bg_invalid)
            } else {
                nameValid = true
                findViewById<ConstraintLayout>(R.id.email_container).setBackgroundResource(R.drawable.bg_valid)
            }


        }
        dateEditText.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                dateValid = false
                findViewById<ConstraintLayout>(R.id.password_layout).setBackgroundResource(R.drawable.name_pwd_bg)
            } else{
                dateValid = true
                findViewById<ConstraintLayout>(R.id.password_layout).setBackgroundResource(R.drawable.bg_valid)
            }

        }
        classEditText.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                classValid = false
                findViewById<ConstraintLayout>(R.id.class_layout).setBackgroundResource(R.drawable.name_pwd_bg)
            } else if (!Validator.isSimpleNameValid(it.toString())) {
                classValid = false
                findViewById<ConstraintLayout>(R.id.class_layout).setBackgroundResource(R.drawable.bg_invalid)
            } else {
                classValid = true
                findViewById<ConstraintLayout>(R.id.class_layout).setBackgroundResource(R.drawable.bg_valid)
            }

        }
        arriveEditText.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                arriveValid = false
                findViewById<ConstraintLayout>(R.id.arrive_layout).setBackgroundResource(R.drawable.name_pwd_bg)
            } else if (!Validator.isSimpleNameValid(it.toString())) {
                arriveValid = false
                findViewById<ConstraintLayout>(R.id.arrive_layout).setBackgroundResource(R.drawable.bg_invalid)
            } else {
                arriveValid = true
                findViewById<ConstraintLayout>(R.id.arrive_layout).setBackgroundResource(R.drawable.bg_valid)
            }

        }
    }


}