package com.example.zxinglib

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.zxing.BitMapUtil
import com.example.zxing.QRCode
import com.example.zxing.QRCodeActivity
import com.example.zxing.SCAN_RESULT
import com.permissionx.linyaodev.PermissionX
import kotlinx.android.synthetic.main.activity_main.*


import java.io.InputStream

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btu.setOnClickListener {

            PermissionX.request(this,
                Manifest.permission.CAMERA
                ){ allGranted, deniedList ->
                    if (allGranted){
                        QRCode.start(this, 1)
                        QRCode.sInstance = this
                    }
            }

        }

        btu1.setOnClickListener {

            val content = "哈哈哈哈"
            val height = 600

            img.setImageBitmap(QRCode.createQRCode(this, content, height, R.drawable.r_c))

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        QRCode.sInstance = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            1 -> {
                if (resultCode == RESULT_OK){
                    if (data != null){
                        val bundle = data.extras
                        val result = bundle?.getString(SCAN_RESULT)
                        Log.e("TAG", "onActivityResult: $result")
                    }
                }
            }
            2 -> {
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        val uri = data.data
                        val imagePath = BitMapUtil.getPicturePathFromUri(this, uri)
//                        对获取到的二维码照片进行压缩
                        val bitmap = BitMapUtil.compressPicture(imagePath)
                        val result = QRCode.codeResult(bitmap)
                        Log.e("TAG", "onActivityResult1111: ${result.text}")
                    }
                }
            }
        }

    }

}