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
    implementation(platform("software.amazon.awssdk:bom:2.17.53"))
    implementation("software.amazon.awssdk:s3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<BootJar> {
    enabled = false
}