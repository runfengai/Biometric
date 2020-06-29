package com.github.biometric

import android.telecom.Call

interface Callback {
    /**
     * 验证成功
     */
    fun onAuthenticateSuccess()

    /**
     * 指纹验证失败
     */
    fun onAuthenticateFail()

    /**
     * 取消验证
     */
    fun onAuthenticateCancel()
}

abstract class FingerPrintCallback : Callback {

    override fun onAuthenticateCancel() {
    }

    override fun onAuthenticateFail() {
    }

    override fun onAuthenticateSuccess() {
    }

    /**
     * 硬件不可用
     */
    abstract fun onHardwareUnavailable()

    /**
     * 未添加指纹
     */
    abstract fun onNoneEnrolled()

    abstract fun onAvailable()
    /**
     * 暂无硬件
     */
    abstract fun onNoHardware()

}

interface FaceCallback : Callback {
    /**
     * 不支持
     */
    fun onHardwareUnavailable()


}