package com.github.biometric

import androidx.fragment.app.FragmentActivity
import com.github.biometric.impl.BiometricType
import com.github.biometric.impl.FaceBiometric
import com.github.biometric.impl.FingerPrintBiometric

/**
 * 管理类，入口
 */
class BiometricManager(builder: Builder) {

    private var iBiometric: IBiometric

    var activity: FragmentActivity
    var title: String
    var subTitle: String
    var description: String
    var negativeBtnText: String
    var fingerPrintCallback: FingerPrintCallback? = null
    var faceCallback: FaceCallback? = null


    init {
        this.activity = builder.activity
        this.title = builder.title
        this.subTitle = builder.subTitle
        this.description = builder.description
        this.negativeBtnText = builder.negativeBtnText
        this.fingerPrintCallback = builder.fingerPrintCallback
        this.faceCallback = builder.faceCallback
        iBiometric = when {
            builder.biometricType == BiometricType.FINGER_PRINT -> FingerPrintBiometric(this)
            builder.biometricType == BiometricType.FACE -> FaceBiometric(this)
            else -> {
                throw IllegalArgumentException("u never got this,if u r smart")
            }
        }
    }

    fun checkState() {
        iBiometric.checkState()
    }

    fun authenticate() {
        iBiometric.authenticate()
    }


    class Builder {
        lateinit var activity: FragmentActivity
        var title: String = ""
        var subTitle: String = ""
        var description: String = ""
        var negativeBtnText: String = ""
        var biometricType: BiometricType = BiometricType.FINGER_PRINT
        var fingerPrintCallback: FingerPrintCallback? = null
        var faceCallback: FaceCallback? = null

        fun activity(activity: FragmentActivity) = apply {
            this.activity = activity
        }

        fun title(title: String) = apply {
            this.title = title
        }

        fun subTitle(subTitle: String) = apply {
            this.subTitle = subTitle
        }

        fun description(description: String) = apply {
            this.description = description
        }

        fun negativeBtnText(negativeBtnText: String) = apply {
            this.negativeBtnText = negativeBtnText
        }

        fun biometricType(biometricType: BiometricType) = apply {
            this.biometricType = biometricType
        }

        fun fingerPrintCallback(fingerPrintCallback: FingerPrintCallback?) = apply {
            this.fingerPrintCallback = fingerPrintCallback
        }

        fun faceCallback(faceCallback: FaceCallback?) = apply {
            this.faceCallback = faceCallback
        }

        fun build(): BiometricManager = BiometricManager(this)

    }

}