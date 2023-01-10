import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.6.21"
    java
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":wetube-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.351")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<BootJar> {
    enabled = false
}