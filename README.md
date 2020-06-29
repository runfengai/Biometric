### Biometric  指纹识别，生物识别。

##### 功能简介：

1.检测是否支持指纹识别。

2.进行指纹识别，自定义指纹识别弹框基本样式。

##### 指纹识别用法：

**1.检测是否支持**

```
lateinit var biometricManager: BiometricManager
private fun supportFingerprint() {
     biometricManager = BiometricManager.Builder()
         .activity(this)
         .title(getString(R.string.fingerprint_hint))
         .negativeBtnText(getString(R.string.cancel))
         .fingerPrintCallback(fingerPrintCallback).build()
     biometricManager.checkState()
 }
```

检测结果回调设置：

```
private var fingerPrintCallback: FingerPrintCallback? = object : FingerPrintCallback() {
    /**
     * =======================================================
     * device check
     * =======================================================
     */
    override fun onHardwareUnavailable() {
        //硬件不可用
    }


    override fun onNoneEnrolled() {
        //暂未添加指纹，请先设置
    }

    @SuppressLint("SetTextI18n")
    override fun onAvailable() {
        //指纹可用
    }

    override fun onNoHardware() {
        //硬件不支持
    }

    /**
     * ============================================================
     * authenticate result
     * ============================================================
     */
    override fun onAuthenticateCancel() {
        Log.e(TAG, "finger->onAuthenticateCancel()")
    }

    override fun onAuthenticateFail() {
        Log.e(TAG, "finger->onAuthenticateFail()")
    }

    override fun onAuthenticateSuccess() {
        Log.e(TAG, "thread------>onAuthenticateSuccess   -->${Thread.currentThread().name}  ")
    }

}
```

**2.进行指纹识别**

```
biometricManager.authenticate()
```

识别结果同上，在fingerPrintCallback回调中。



### TODO

目前存在的问题，'androidx.biometric:biometric:1.1.0-alpha01' 中自定义取消按钮不可用，内部问题，等待官方发布新版本修复。

