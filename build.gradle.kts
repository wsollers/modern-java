// build.gradle.kts (ROOT)
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.api.plugins.JavaPluginExtension

plugins {
    id("org.springframework.boot") version "4.0.1" apply false
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
    // Apply Java + dependency management everywhere
    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")

    // ✅ Import BOMs so module deps can omit versions
    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:4.0.1")
            mavenBom("com.azure.spring:spring-cloud-azure-dependencies:5.23.0")
        }
    }

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
