group = "ru.cs.volchenko.linkmonitoringapp"
version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot")
    kotlin("plugin.jpa")
}

dependencies{

    implementation(project(mapOf("path" to ":link-parser")))
    implementation(project(mapOf("path" to ":field-extractor")))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.kafka:spring-kafka")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

springBoot {
    mainClass = "ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.ScrapperApplicationKt"
}
