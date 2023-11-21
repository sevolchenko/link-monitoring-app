import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()

}

plugins {
    java
    kotlin("jvm")

    kotlin("plugin.spring")
    id("io.spring.dependency-management")

    id("io.gitlab.arturbosch.detekt")
}

subprojects {

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        val mockitoKotlinVersion: String by project
        val detektVersion: String by project

        implementation(kotlin("stdlib-jdk8"))

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.detekt {
        autoCorrect = true
    }

    tasks.check {
        dependsOn("detekt")
    }
}