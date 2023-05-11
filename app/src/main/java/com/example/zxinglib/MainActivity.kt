package com.example.zxinglib

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.zxing.QRCode
import com.example.zxing.QRCodeActivity
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
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE){ allGranted, deniedList ->
                    if (allGranted){
                        QRCode.start(this, 1)
                    }
            }

        }

        btu1.setOnClickListener {

            val content = "哈哈哈哈"
            val height = 600

            img.setImageBitmap(QRCode.createQRCode(this, content, height, R.drawable.r_c))

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK){
            if (data != null){
                val bundle = data.extras
                val result = bundle?.getString("SCAN_RESULT")
                Log.e("TAG", "onActivityResult: $result")
            }
        }

    }

}