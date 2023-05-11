package com.example.zxinglib

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.zxing.QRCode
import com.example.zxing.QRCodeActivity
import com.permissionx.linyaodev.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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