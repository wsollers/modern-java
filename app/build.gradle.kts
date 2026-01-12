plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    application
}

dependencies {
    // Import BOMs internally (NOT exported)
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
    implementation(platform("com.azure.spring:spring-cloud-azure-dependencies:5.23.0"))

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

    // Azure Key Vault (versionless, now resolved via Azure BOM)
    implementation("com.azure.spring:spring-cloud-azure-starter-keyvault-secrets")
}

application {
    mainClass.set("dev.wsollers.application.ApplicationStart")
}

tasks.test {
    useJUnitPlatform()
}