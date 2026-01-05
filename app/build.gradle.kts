plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    application
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utilities"))
    implementation(project(":web"))

    implementation("org.springframework.boot:spring-boot-starter")        // provides SpringBootApplication
    implementation("org.springframework.boot:spring-boot-starter-web")    // REST
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.postgresql:postgresql:42.7.8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Belt + suspenders for Gradle 9 / IDE weirdness:
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.azure.spring:spring-cloud-azure-starter-keyvault-secrets")
}

application {
    // adjust to your real main class
    mainClass.set("dev.wsollers.application.ApplicationStart")
}

tasks.test {
    useJUnitPlatform()
}