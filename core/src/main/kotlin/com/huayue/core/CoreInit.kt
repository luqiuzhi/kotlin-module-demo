package com.huayue.core

import cn.hutool.core.util.ClassUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.extra.spring.SpringUtil
import cn.hutool.setting.yaml.YamlUtil
import com.huayue.core.function.Fun
import com.huayue.core.function.FunCache
import com.huayue.core.function.FunMeta
import com.huayue.core.module.BootModules
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/10
 */
// 在应用启动时，扫描路径下使用了@Fun注解的类，并返回一个FunMeta列表，存储到FunCache中
fun initFunMeta(packagePath: String) {
    val funMetaList = mutableListOf<FunMeta>()
    // 根据包路径扫描@Fun注解的方法，并构造FunMeta，存入FunCache中
    val classes = ClassUtil.scanPackage(packagePath)
    classes.filter { clazz -> !clazz.isInterface }.forEach { clazz ->
        val methods = clazz.methods
        methods.forEach { method ->
            val funAnnotation = method.getAnnotation(Fun::class.java)
            // TODO: huay 2024/9/12 针对需要生成远程服务的接口，提供一个逻辑判断
            if (funAnnotation != null) {
                val funMeta = FunMeta(clazz.packageName, clazz.simpleName, method.name, funAnnotation)
                funMetaList.add(funMeta)
                // 动态注册一个spring接口
                // TODO: huay 2024/9/12 完善注册方式，是否使用Spring的方式
                registerFunMeta(funMeta, method)
            }
        }
    }
    FunCache.setCache(funMetaList)
}

fun registerFunMeta(funMeta: FunMeta, method: Method) {
    val requestRegister = SpringUtil.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping::class.java)
    // 动态注册一个spring接口
    val path = "/${funMeta.funAnnotation.module}/${funMeta.funName}"
    val request = RequestMappingInfo.paths(path).methods(RequestMethod.POST).build()
    requestRegister.registerMapping(request, StrUtil.lowerFirst(funMeta.className), method)
}

fun initBootModule() {
    val config = YamlUtil.loadByPath("application.yaml")
    val bootModules = config["modules"]
    bootModules?.let {
        val modules = it as ArrayList<String>
        BootModules.addBootModules(modules.toSet())
    }
}