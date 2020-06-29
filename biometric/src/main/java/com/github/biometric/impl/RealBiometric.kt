package com.github.biometric.impl

import com.github.biometric.BiometricManager
import com.github.biometric.IBiometric

/**
 * 两种
 */
enum class BiometricType {
    FINGER_PRINT,
    FACE
}

/**
 * 实现类
 */
abstract class RealBiometric constructor(
    biometricManager: BiometricManager,
    biometricType: BiometricType = BiometricType.FINGER_PRINT
) :
    IBiometric {

}