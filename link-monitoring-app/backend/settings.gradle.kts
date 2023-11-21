rootProject.name = "link-monitoring-app"

include("link-parser")
include("scrapper")
include("field-extractor")

pluginManagement {
    val kotlinVersion: String by settings
    val detektVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    plugins {

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion

        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        //id("io.gitlab.arturbosch.detekt") version detektVersion
    }
}
