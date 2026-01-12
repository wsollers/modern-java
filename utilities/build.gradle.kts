plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Import BOM internally (NOT exported)
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))

    // Versionless dependency — now resolved via BOM
    api("org.slf4j:slf4j-api")
}