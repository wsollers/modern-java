plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
    application
}

dependencies {
    // Only Azure BOM — Boot BOM is already applied by the Boot plugin
    //implementation(platform("com.azure.spring:spring-cloud-azure-dependencies:7.0.0-beta.1"))

    implementation(project(":domain"))
    implementation(project(":utilities"))
    implementation(project(":web"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.postgresql:postgresql:42.7.8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //implementation("com.azure.spring:spring-cloud-azure-starter-keyvault-secrets")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

application {
    mainClass.set("dev.wsollers.application.ApplicationStart")
}

tasks.test {
    useJUnitPlatform()
}