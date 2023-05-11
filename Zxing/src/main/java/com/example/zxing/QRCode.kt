package com.example.zxing

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.king.zxing.util.CodeUtils

object QRCode {

    fun start(activity: Activity, requestCode: Int){

        val intent = Intent(activity, QRCodeActivity::class.java)
        activity.startActivityForResult(intent, requestCode)

    }

    @SuppressLint("ResourceType")
    fun createQRCode(activity: Activity, content: String, heightPix: Int, drawable: Int): Bitmap{
        val i = activity.resources.openRawResource(drawable)
        val bitmap = BitmapFactory.decodeStream(i)
       return CodeUtils.createQRCode(content, heightPix, bitmap)
    }

}