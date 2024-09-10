package com.huayue.producer

import com.huayue.core.initBootModule
import com.huayue.core.initFunMeta
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/** spring boot 启动类
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */

@SpringBootApplication
@EnableDiscoveryClient
open class AppProducer

fun main(args: Array<String>) {
    runApplication<AppProducer>(*args)
    // 方法元数据生成
    initFunMeta("com.huayue")
    // 模块元数据生成
    initBootModule()
//    println(FunCache.cache.funMeta.size)
//    val executeFun = executeFun("testApi", "producer", "this is really param")
//    println(executeFun)
}