// build.gradle.kts (ROOT)



allprojects {
    group = "dev.wsollers"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "4.0.1" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

subprojects {
    // Apply Java + dependency management everywhere
    apply(plugin = "java-library")



    dependencies {
        "testImplementation"(platform("org.junit:junit-bom:5.11.4"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(23))
        }
    }
}
