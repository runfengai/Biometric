package com.github.biometric.impl

import com.github.biometric.BiometricManager


/**
 * 面部识别
 */
class FaceBiometric(biometricManager: BiometricManager) :
    RealBiometric(biometricManager, biometricType = BiometricType.FACE) {
    override fun checkState() {

    }

    override fun authenticate() {

    }

}