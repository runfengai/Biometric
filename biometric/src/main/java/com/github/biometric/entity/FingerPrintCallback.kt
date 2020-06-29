package com.github.biometric.entity

/**
 * 识别结果，共性，指纹/面部
 */
enum class BaseState {
    /**
     * 验证成功
     */
    AuthenticateSuccess,
    /**
     * 指纹验证失败
     */
    AuthenticateFail,
    /**
     * 取消验证
     */
    AuthenticateCancel
}


enum class FingerPrintState {
    /**
     * 硬件不支持
     */
    HardwareUnavailable,
    /**
     * 未添加指纹
     */
    NoneEnrolled,

}

/**
 * todo
 */
enum class FaceState {
    HardwareUnavailable
}


sealed class BiometricState {

    data class Default(val baseState: BaseState) : BiometricState()

    data class FingerPrint(val fingerPrintCallback: FingerPrintState) : BiometricState()

    data class Face(val fingerPrintCallback: FingerPrintState) : BiometricState()
}