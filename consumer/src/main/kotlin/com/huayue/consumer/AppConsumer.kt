package com.huayue.consumer

import com.huayue.core.function.initFunMeta
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
}