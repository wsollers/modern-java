plugins {
  `java-library`
}

dependencies {
  // Import BOMs internally (NOT exported)
  implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
  implementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))

  // Internal modules
  api(project(":domain"))
  implementation(project(":utilities"))

  // Spring Boot starters (versionless)
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-validation")

  // Test starter (versionless)
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // Test fixtures
  testImplementation(testFixtures(project(":test-support")))

  // PostgreSQL driver
  runtimeOnly("org.postgresql:postgresql")

  // Jackson XML
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // Boot 4: TestRestTemplate lives here
  testImplementation("org.springframework.boot:spring-boot-resttestclient")
  testImplementation("org.springframework.boot:spring-boot-restclient")
}