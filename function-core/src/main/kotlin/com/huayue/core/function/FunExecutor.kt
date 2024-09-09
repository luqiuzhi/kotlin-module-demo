package com.huayue.core.function

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */

fun executeFun(funName: String, module: String, vararg args: Any): Any? {
    val funMeta = FunCache.cache.funMeta.find { it.funName == funName && it.funAnnotation.module == module }
    if (funMeta != null) {
        // 执行本地调用
        val classPath = funMeta.packagePath + "." + funMeta.className
        val clazz = Class.forName(classPath)
        val instance = clazz.getDeclaredConstructor().newInstance()
        val methods = clazz.methods
        val find = methods.find { method -> method.name == funMeta.funName }
        // 参数处理，此处默认取第一个参数转化为字符串
        // TODO: huay 2024/9/9 此处应该使用完整的参数处理方法
        val result = find?.invoke(instance, args[0].toString())
        return result
    } else {
        // 执行远程调用
        println("未找到对应方法")
        // 报错
        return null
    }
}