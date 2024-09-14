package com.huayue.core.function

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Fun(val funName: String, val module: String, val isApi: Boolean = true)
// TODO: huay 2024/9/11 函数注解设计，和函数元数据结合考虑，需要包含函数的参数列表和返回值类型
