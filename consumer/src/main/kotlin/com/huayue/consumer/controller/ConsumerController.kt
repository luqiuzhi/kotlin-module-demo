package com.huayue.consumer.controller

import com.huayue.core.function.executeFun
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
@RestController
class ConsumerController {
    @GetMapping("/test")
    fun testFunExecution(): String {
        val result = executeFun("testApi", "producer", "from consumer")
        return result.toString()
    }
}