import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()
}

plugins {
    java
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

subprojects {

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        val mockitoKotlinVersion: String by project

        implementation(kotlin("stdlib-jdk8"))

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

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

    //    extensions.configure(DetektExtension::class) {
//        toolVersion = detektVersion
//        buildUponDefaultConfig = true
//        reports {
//            html.enabled = true
//        }
//    }

//    tasks.check {
//        dependsOn("detekt")
//    }
}