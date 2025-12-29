plugins {
    id("org.springframework.boot") version "3.2.12"
    id("io.spring.dependency-management") version "1.1.6"
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
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.4")

}

application {
    // adjust to your real main class
    mainClass.set("dev.wsollers.application.ApplicationStart")
}

tasks.test {
    useJUnitPlatform()
}