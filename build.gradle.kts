plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "org.gellertm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}