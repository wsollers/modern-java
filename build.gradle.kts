// build.gradle.kts (ROOT)

plugins {
    id("org.springframework.boot") version "4.0.1" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    id("org.cyclonedx.bom") version "2.1.0"
}

allprojects {
    group = "dev.wsollers"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

subprojects {

    // Apply the right Java plugin depending on module type
    if (name == "app") {
        apply(plugin = "java") // app uses Boot + application plugin in its own build.gradle.kts
    } else {
        apply(plugin = "java-library")
    }

    // Java 23 toolchain everywhere Java is applied
    plugins.withId("java") {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(23))
            }
        }
    }

    // JUnit Platform everywhere tests exist
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    // Ensure Gradle test executor can start the JUnit Platform
    dependencies {
        add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}
