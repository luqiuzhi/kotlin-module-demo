package com.huayue.core.function

/** 基于注解Fun定义数据结构进行存储
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
data class FunMeta(val packagePath: String, val className: String, val funName: String, val funAnnotation: Fun)