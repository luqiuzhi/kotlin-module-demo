package com.huayue.core.function

import cn.hutool.extra.spring.SpringUtil
import cn.hutool.http.HttpUtil
import com.alibaba.nacos.api.naming.NamingFactory
import com.alibaba.nacos.api.naming.NamingService
import com.huayue.core.module.BootModules

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
fun executeFun(funName: String, module: String, vararg args: Any): Any? {
    // TODO: huay 2024/9/11 函数执行器作为函数执行流程的同一入口，内部调用不同的函数处理器进行操作，考虑使用命令模式或责任链模式
    // 查询是否有beforeHook
    // 执行beforeHook
    if (isCurrentBoot(module) || BootModules.isBootModule(module)) {
        // TODO: huay 2024/9/11 增加本地方法调用的执行器
        // 执行本地调用
        val funMeta = FunCache.cache.funMeta.find { it.funName == funName && it.funAnnotation.module == module }
        if (funMeta != null) {
            val classPath = funMeta.packagePath + "." + funMeta.className
            val clazz = Class.forName(classPath)
            val instance = clazz.getDeclaredConstructor().newInstance()
            val methods = clazz.methods
            // TODO: huay 2024/9/11 方法的定位需要考虑重载的情况
            val find = methods.find { method -> method.name == funMeta.funName }
            // 参数处理，此处默认取第一个参数转化为字符串
            // TODO: huay 2024/9/9 此处应该使用完整的参数处理方法
            val result = find?.invoke(instance, args[0].toString())
            return result
        } else {
            // TODO: huay 2024/9/11 找不到方法时的报错返回
            println("未找到对应方法")
            // 报错
            return null
        }
    } else {
        // TODO: huay 2024/9/11 增加远程方法调用的执行器
        // 执行远程调用
        // 读取nacos的配置，创建代理
        // TODO: huay 2024/9/11 在分布式环境下需要读取集群配置，创建httpclient
        val property = SpringUtil.getProperty("spring.cloud.nacos.discovery.server-addr")
        val naming: NamingService = NamingFactory.createNamingService(property)
        val instance = naming.selectOneHealthyInstance(module)
        val paramMap = mapOf("str" to args[0].toString())
        val result: String = HttpUtil.post("${instance.ip}:${instance.port}/$module/$funName", paramMap)
        return result
    }
}

fun isCurrentBoot(module: String) = module == SpringUtil.getApplicationName()