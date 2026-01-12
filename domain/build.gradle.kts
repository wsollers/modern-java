plugins {
  `java-library`
  id("io.freefair.lombok") version "8.11"
}

dependencies {
  // Import BOMs internally (NOT exported)
  implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
  implementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))

  // Jackson (versionless)
  api("com.fasterxml.jackson.core:jackson-annotations")
  api("com.fasterxml.jackson.core:jackson-databind")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

  // Internal modules
  implementation(project(":utilities"))
  implementation("org.slf4j:slf4j-api")

  // Testcontainers (versionless)
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:postgresql")

  // Postgres driver for tests
  testRuntimeOnly("org.postgresql:postgresql")

  // Test fixtures
  testImplementation(testFixtures(project(":test-support")))
}

tasks.test {
  useJUnitPlatform()
}