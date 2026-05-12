plugins {
    kotlin("jvm")
    application
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}

application {
    mainClass.set("com.example.lab1.Bai1Kt")
}

tasks.register<JavaExec>("bai1") {
    group = "verification"
    mainClass.set("com.example.lab1.Bai1Kt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

tasks.register<JavaExec>("bai2") {
    group = "verification"
    mainClass.set("com.example.lab1.Bai2Kt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

tasks.register<JavaExec>("bai3") {
    group = "verification"
    mainClass.set("com.example.lab1.Bai3Kt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

tasks.register<JavaExec>("bai4") {
    group = "verification"
    mainClass.set("com.example.lab1.Bai4Kt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

tasks.register<JavaExec>("bai5") {
    group = "verification"
    mainClass.set("com.example.lab1.Bai5Kt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

