package com.example.smartwatchunlocker.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.smartwatchunlocker.R
import com.google.android.material.imageview.ShapeableImageView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainScreen : AppCompatActivity() {


    var permissionCallback: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val localTime = LocalTime.now()
//        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm")
        findViewById<TextView>(R.id.time_tv).text = localTime.format(dateTimeFormatter)


        findViewById<CardView>(R.id.login_btn).setOnClickListener {
            findViewById<ShapeableImageView>(R.id.logo_ivv).setColorFilter(ContextCompat.getColor(this, R.color.activated_clr), android.graphics.PorterDuff.Mode.SRC_IN)

        }
        findViewById<CardView>(R.id.mark_card).setOnClickListener {
            startActivity(Intent(this@MainScreen, AttendanceMarkScreen::class.java))
        }

        findViewById<TextView>(R.id.show_attendance).setOnClickListener {
            startActivity(Intent(this@MainScreen, AttendanceScreen::class.java))
        }

        findViewById<CardView>(R.id.current_loc_card).setOnClickListener {
//            methodRequiresPermissions()
            uploadMeNowImage()

        }
        permissionCallback = {
            if (it) {

            }
        }
    }

    private fun uploadMeNowImage() {
        try {

            Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            startActivity(Intent(this@MainScreen, MapScreen::class.java))
                            findViewById<ShapeableImageView>(R.id.curr_loc_iv).setColorFilter(ContextCompat.getColor(this@MainScreen, R.color.activated_clr), android.graphics.PorterDuff.Mode.SRC_IN)
                        } else if (report.isAnyPermissionPermanentlyDenied) {
                            //LoaderDialog.dismissDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest?>?,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                })
                .onSameThread()
                .check()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    private val quickPermissionsOption = QuickPermissionsOptions(
//        handleRationale = true,
//        permanentlyDeniedMessage = "Permission denied permanently",
//        rationaleMethod = { req -> permissionDeniedOnce(req) },
//        permanentDeniedMethod = { req -> permissionsPermanentlyDenied(req) }
//    )

//    private fun methodRequiresPermissions(permissionString: String?= null) = runWithPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, options = quickPermissionsOption) {
//        permissionCallback?.invoke(true)
//    }
//
//    private fun permissionDeniedOnce(req: QuickPermissionsRequest) {
//        permissionCallback?.invoke(false)
//        // this will be called when permission is denied once or more time. Handle it your way
//        AlertDialog.Builder(this)
//            .setTitle("Permission Needed")
//            .setMessage("Beme need this permission show contacts. Press OK in order to proceed")
//            .setPositiveButton("OK") { dialog, which -> req.proceed() }
//            .setNegativeButton("cancel") { dialog, which -> req.cancel() }
//            .setCancelable(false)
//            .show()
//    }

//    private fun permissionsPermanentlyDenied(req: QuickPermissionsRequest) {
//        AlertDialog.Builder(this)
//            .setTitle("Permission Needed")
//            .setMessage("App needs access to this permission to function properly. Go to settings by pressing OK btn and allow contacts permission")
//            .setPositiveButton("OK") { dialog, which ->
//                permissionCallback?.invoke(false)
//                req.openAppSettings()
//            }
//            .setNegativeButton("Cancel") { dialog, which ->
//                permissionCallback?.invoke(false)
//                req.cancel()
//            }
//            .setCancelable(false)
//            .show()
//
//    }
    override fun onResume() {
        super.onResume()
        if (AttendanceMarkScreen.donneMarked) {
            findViewById<ShapeableImageView>(R.id.mar_at_iv).setColorFilter(ContextCompat.getColor(this, R.color.activated_clr), android.graphics.PorterDuff.Mode.SRC_IN)
        }else{
            findViewById<ShapeableImageView>(R.id.mar_at_iv).setColorFilter(ContextCompat.getColor(this, R.color.not_activated), android.graphics.PorterDuff.Mode.SRC_IN)

        }
    }
}