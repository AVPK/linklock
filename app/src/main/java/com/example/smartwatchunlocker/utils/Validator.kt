package com.example.smartwatchunlocker.utils

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

object Validator {
    private const val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    private const val USERNAME_REGEX = "^[A-Za-z0-9_-]{2,16}\$"
    private const val FULL_NAME_REGEX = "(?i)(^[a-z])((?![?,'-]$)[ ]?[a-z. ]){2,24}$"
    private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";

    private val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    fun validateEmail(hex: String? = null): Boolean {
        matcher = pattern.matcher(hex.toString())
        return matcher!!.matches()

    }

    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    fun isPhoneNumberValid(number: String): Boolean {
        return number.isNotBlank() && Patterns.PHONE.matcher(number).matches() && number.length>=10
    }

    /*fun isPasswordValid(pass: String): Boolean {
        //return PASS_REGEX.toRegex().matches(pass);
        return pass.length > 5
    }*/

    fun isPasswordValid(pass: String): Boolean {
        return PASSWORD_PATTERN.toRegex().matches(pass);
    }

    fun isZipCodeValid(zipCode: String): Boolean {
        return zipCode.length > 4
    }

    fun isPartnerCodeValid(zipCode: String): Boolean {
        return zipCode.length == 6
    }

    fun isUserNameValid(username: String): Boolean {
        return USERNAME_REGEX.toRegex().matches(username)
    }
    fun isSimpleNameValid(username: String): Boolean {
        return FULL_NAME_REGEX.toRegex().matches(username)
    }

    fun isNameValid(fullName: String): Boolean {
        var name = fullName
        if (name.isNotEmpty())
            name = name.trim()
        return FULL_NAME_REGEX.toRegex().matches(name)
    }
}