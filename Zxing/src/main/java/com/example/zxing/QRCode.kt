package com.example.zxing

import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import com.king.zxing.util.CodeUtils
import java.util.*


object QRCode {

    var sInstance: Activity? = null

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

    fun albumStart(activity: Activity, requestCode: Int){
        val intent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(intent, requestCode)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun codeResult(bitmap: Bitmap): Result{
        return setZxingResult(bitmap)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setZxingResult(bitmap: Bitmap): Result{
        val picWidth: Int = bitmap.width
        val picHeight: Int = bitmap.height
        val pix = IntArray(picWidth * picHeight)
        //Log.e(TAG, "decodeFromPicture:图片大小： " + bitmap.getByteCount() / 1024 / 1024 + "M");
        //Log.e(TAG, "decodeFromPicture:图片大小： " + bitmap.getByteCount() / 1024 / 1024 + "M");
        bitmap.getPixels(pix, 0, picWidth, 0, 0, picWidth, picHeight)
        //构造LuminanceSource对象
        //构造LuminanceSource对象
        val rgbLuminanceSource = RGBLuminanceSource(
            picWidth, picHeight, pix
        )
        val bb = BinaryBitmap(HybridBinarizer(rgbLuminanceSource))
        //因为解析的条码类型是二维码，所以这边用QRCodeReader最合适。
        //因为解析的条码类型是二维码，所以这边用QRCodeReader最合适。
        val qrCodeReader = QRCodeReader()
        val hints: MutableMap<DecodeHintType, Any?> = EnumMap(
            DecodeHintType::class.java
        )
        hints[DecodeHintType.CHARACTER_SET] = "utf-8"
        hints[DecodeHintType.TRY_HARDER] = true
        val result: Result
        return qrCodeReader.decode(bb, hints)

        }
}