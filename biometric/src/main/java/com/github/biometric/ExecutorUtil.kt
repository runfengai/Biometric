package com.github.biometric

import java.util.concurrent.Executor
import java.util.concurrent.Executors

object ExecutorUtil {
    fun getExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }
}
