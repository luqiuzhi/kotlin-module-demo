package com.huayue.core.function

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/9
 */
data class FunCache (var funMeta: List<FunMeta> = emptyList()) {

    companion object{
        var cache = FunCache()

        fun setCache(funMeta: List<FunMeta>){
            cache = FunCache(funMeta)
        }
    }
}