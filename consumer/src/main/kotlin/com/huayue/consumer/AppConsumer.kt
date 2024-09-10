package com.huayue.consumer

import com.huayue.core.initBootModule
import com.huayue.core.initFunMeta
import com.huayue.core.module.BootModules
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = ["com.huayue"])
open class AppConsumer

fun main(args: Array<String>) {
    runApplication<AppConsumer>(*args)
    initFunMeta("com.huayue")
    initBootModule()
    println(BootModules.isBootModule("producer"))
}