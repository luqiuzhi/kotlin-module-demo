package com.huayue.core.function

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Fun(val funName: String, val module: String, val isApi: Boolean = true)
