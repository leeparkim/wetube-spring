plugins {
    kotlin("jvm") version "1.6.21"
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":wetube-domain"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}