package com.huayue.producer

import com.huayue.core.function.Fun
import com.huayue.demo.api.TestApi
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
@Component
class TestProducer : TestApi {
    @Fun(funName = "testApi", module = "producer")
    @ResponseBody
    override fun testApi(@RequestParam("str") str: String): String {
        return "服务提供者api：$str"
    }
}