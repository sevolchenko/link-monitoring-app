group = "ru.cs.volchenko.linkmonitoringapp"
version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot")
    kotlin("plugin.jpa")
}

dependencies{

    val springdocVersion: String by project
    val mapstructVersion: String by project

    implementation(project(mapOf("path" to ":link-parser")))
    implementation(project(mapOf("path" to ":field-extractor")))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springdoc:springdoc-openapi-ui:$springdocVersion")

    implementation("org.springframework.kafka:spring-kafka")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Amapstruct.defaultComponentModel=spring")
}

springBoot {
    mainClass = "ru.vsu.cs.volchenko.linkmonitoringapp.scrapper.ScrapperApplicationKt"
}
