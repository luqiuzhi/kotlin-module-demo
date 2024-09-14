package com.huayue.core.module

/**
 * @author huay@cecjiutian.com
 * @date 2024/9/10
 */
object BootModules {
    // TODO: huay 2024/9/12 保存当前的组件运行信息，如果是使用SOFAArk架构，则需要从内存中获取
    private val bootModules: MutableSet<String> = mutableSetOf()

    fun addBootModule(module: String) {
        bootModules.add(module)
    }

    fun isBootModule(module: String): Boolean {
        return bootModules.contains(module)
    }

    // 批量添加module
    fun addBootModules(modules: Set<String>) {
        bootModules.addAll(modules)
    }
}