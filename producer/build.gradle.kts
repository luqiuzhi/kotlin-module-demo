plugins {
    kotlin("jvm") version "2.0.10"
}

group = "com.huayue"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":function-core"))
    implementation(project(":producer-api"))
    // spring boot web
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.0")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2023.0.1.2")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}