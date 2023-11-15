plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "ru.cs.volchenko.linkmonitoringapp"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.hamcrest:hamcrest:2.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")

}

tasks.test {
    useJUnitPlatform()
}