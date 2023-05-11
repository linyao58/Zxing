package com.example.zxing

import android.app.Activity
import android.content.Intent

object QRCode {

    fun start(activity: Activity, requestCode: Int){

        val intent = Intent(activity, QRCodeActivity::class.java)
        activity.startActivityForResult(intent, requestCode)

    }

}