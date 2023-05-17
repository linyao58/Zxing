# Zxing
二维码库

QRCode.start()方法实现扫码功能

QRCode.createQRCode()方法实现生成二维码功能

QRCode.albumStart()方法进入相册中实现扫码功能

在项目的app模块的build.gradle文件中加入implementation 'com.google.zxing:core:3.5.1'

QRCode.sInstance = this获取当前activity实例

在onDestroy中QRCode.sInstance设置空值
override fun onDestroy() {
        super.onDestroy()
        QRCode.sInstance = null
    }