package com.github.biometric

import android.os.Build
import android.os.Build.VERSION.SDK_INT

object Utils {
    /**
     * 9.0
     */
    fun isAndroidP(): Boolean {
        return SDK_INT >= Build.VERSION_CODES.P
    }

    /**
     * 6.0
     */
    fun isAndroidM(): Boolean {
        return SDK_INT >= Build.VERSION_CODES.M
    }

}