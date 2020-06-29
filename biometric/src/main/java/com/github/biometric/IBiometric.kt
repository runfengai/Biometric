package com.github.biometric


/**
 * 接口
 */
interface IBiometric {
    /**
     * 检查fingerPrint状态
     */
    fun checkState()

    /**
     * 进行验证
     */
    fun authenticate()


}