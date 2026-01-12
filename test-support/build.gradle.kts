plugins {
    `java-library`
    `java-test-fixtures`
    id("io.freefair.lombok") version "8.11"
}

dependencies {
    // Main BOMs (for main + test)
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
    implementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))

    // Test fixtures BOMs
    testFixturesImplementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
    testFixturesImplementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))

    // Main dependencies — FIXED
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("org.springframework.data:spring-data-jpa")
    implementation(project(":utilities"))
    implementation("org.slf4j:slf4j-api")

    // Test dependencies
    testRuntimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")

    // Test fixtures dependencies
    testFixturesApi("org.springframework:spring-test")
    testFixturesApi("org.testcontainers:junit-jupiter")
    testFixturesApi("org.testcontainers:postgresql")
    testFixturesApi("org.slf4j:slf4j-api")
    testFixturesApi("com.fasterxml.jackson.core:jackson-annotations")
    testFixturesApi("com.fasterxml.jackson.core:jackson-databind")
}