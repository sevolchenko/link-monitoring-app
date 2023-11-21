import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "ru.cs.volchenko.linkmonitoringapp"
version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot")
    `java-library`
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
