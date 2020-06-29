package com.github.biometric.impl

import androidx.biometric.BiometricConstants.ERROR_USER_CANCELED
import androidx.biometric.BiometricManager.*
import androidx.biometric.BiometricPrompt
import com.github.biometric.*


/**
 * 指纹识别
 */
open class FingerPrintBiometric(val biometricManager: BiometricManager) :
    RealBiometric(biometricManager, BiometricType.FINGER_PRINT) {

    val promptInfo: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(biometricManager.title)
        .setSubtitle(biometricManager.subTitle)
        .setDescription(biometricManager.description)
        .setNegativeButtonText(biometricManager.negativeBtnText)
        .build()

    val biometricPrompt =
        BiometricPrompt(biometricManager.activity, ExecutorUtil.getExecutor(), object :
            BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                when (errorCode) {
                    BiometricPrompt.ERROR_HW_UNAVAILABLE -> {
                        biometricManager.fingerPrintCallback?.onHardwareUnavailable()//硬件不支持
                    }
                    BiometricPrompt.ERROR_NEGATIVE_BUTTON, ERROR_USER_CANCELED -> //用户按取消键盘，手动取消
                        biometricManager.fingerPrintCallback?.onAuthenticateCancel()
                    BiometricPrompt.ERROR_NO_BIOMETRICS -> {//暂无添加生物特征（如指纹）
                        biometricManager.fingerPrintCallback?.onNoneEnrolled()
                    }
                    else -> biometricManager.fingerPrintCallback?.onAuthenticateFail()
                }
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                biometricManager.fingerPrintCallback?.onAuthenticateSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                biometricManager.fingerPrintCallback?.onAuthenticateFail()
            }
        })
    /**
     *
     */
    private var androidMFingerPrint = AndroidMFingerPrint()

    private var androidPFingerPrint = AndroidPFingerPrint()

    override fun checkState() {
        when {
            Utils.isAndroidM() -> androidMFingerPrint.checkState()
            Utils.isAndroidP() -> androidPFingerPrint.checkState()
        }
    }

    override fun authenticate() {
        when {
            Utils.isAndroidM() -> androidMFingerPrint.authenticate()
            Utils.isAndroidP() -> androidPFingerPrint.authenticate()
        }
    }

    /**
     * androidP版本
     */
    inner class AndroidPFingerPrint : IBiometric {
        override fun checkState() {

        }

        override fun authenticate() {

        }
    }

    /**
     * androidM版本
     */
    inner class AndroidMFingerPrint : IBiometric {

        override fun checkState() {

            val state = from(biometricManager.activity)
                .canAuthenticate()

            when (state) {
                BIOMETRIC_ERROR_HW_UNAVAILABLE, BIOMETRIC_ERROR_NO_HARDWARE -> {//硬件不支持
                    biometricManager.fingerPrintCallback?.onHardwareUnavailable()
                }
                BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    biometricManager.fingerPrintCallback?.onNoneEnrolled()//未设置
                }
                BIOMETRIC_SUCCESS -> {
                    biometricManager.fingerPrintCallback?.onAvailable()
                }
                else -> {//BIOMETRIC_ERROR_NO_HARDWARE
                    biometricManager.fingerPrintCallback?.onNoHardware()
                }
            }
        }

        override fun authenticate() {
            biometricPrompt.authenticate(promptInfo)
        }

    }

}