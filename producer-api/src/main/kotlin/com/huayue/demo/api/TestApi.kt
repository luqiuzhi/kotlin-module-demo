package com.huayue.demo.api

import com.huayue.core.function.Fun

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
interface TestApi {
    @Fun(funName = "testApi", module = "producer") fun testApi(str: String): String
}