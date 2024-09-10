plugins {
    kotlin("jvm") version "2.0.10"
}

group = "com.huayue"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
    implementation("cn.hutool:hutool-all:5.8.26")
    implementation("com.alibaba.nacos:nacos-client:2.3.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}