plugins {
    `java-library`
    `java-test-fixtures`
    id("io.spring.dependency-management")
    id("io.freefair.lombok") version "8.11"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.1")
        mavenBom("org.testcontainers:testcontainers-bom:1.20.4")
    }
}

dependencies {
    // Main dependencies
    api(platform("org.springframework.boot:spring-boot-dependencies:3.4.1"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":utilities"))
    implementation("org.slf4j:slf4j-api")

    // Test dependencies
    testRuntimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")

    // Test fixtures dependencies - THIS IS WHAT'S MISSING
    testFixturesApi("org.springframework:spring-test")
    testFixturesApi("org.testcontainers:junit-jupiter")
    testFixturesApi("org.testcontainers:postgresql")
    testFixturesApi("org.slf4j:slf4j-api")

    testFixturesApi("com.fasterxml.jackson.core:jackson-annotations")
    testFixturesApi("com.fasterxml.jackson.core:jackson-databind")
}