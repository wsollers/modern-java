// build.gradle.kts (ROOT)
allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "3.2.12" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "dev.wsollers"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    // Apply Java plugin to *all* modules so `implementation(...)` exists everywhere.
    apply(plugin = "java-library")
    // Import Spring Boot BOM everywhere so you can omit versions.
    apply(plugin = "io.spring.dependency-management")

    plugins.withId("java") {
        dependencies {
            "testImplementation"(platform("org.junit:junit-bom:5.11.4"))
            "testImplementation"("org.junit.jupiter:junit-jupiter")
            "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
        }
        tasks.withType<Test>().configureEach { useJUnitPlatform() }
    }

    tasks.withType(Test::class.java).configureEach {
            useJUnitPlatform()
    }

    tasks.withType(JavaCompile::class.java).configureEach {
        options.encoding = "UTF-8"
    }
}
