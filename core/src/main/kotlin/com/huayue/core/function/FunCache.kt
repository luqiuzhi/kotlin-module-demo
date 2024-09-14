package com.huayue.core.function

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
data class FunCache (var funMeta: List<FunMeta> = emptyList()) {
    // TODO: huay 2024/9/11 函数元数据的缓存格式，需要结合函数元数据进行考虑
    companion object{
        var cache = FunCache()

        fun setCache(funMeta: List<FunMeta>){
            cache = FunCache(funMeta)
        }
    }
}