plugins {
  `java-library`
  id("io.freefair.lombok") version "8.11"
}

dependencies {
  implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
  testImplementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))

  implementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))
  testImplementation(platform("org.testcontainers:testcontainers-bom:1.20.4"))

  implementation("jakarta.persistence:jakarta.persistence-api")
  implementation("org.springframework.data:spring-data-jpa")

  implementation("com.fasterxml.jackson.core:jackson-annotations")
  implementation("com.fasterxml.jackson.core:jackson-databind")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

  implementation(project(":utilities"))
  implementation("org.slf4j:slf4j-api")

  // Tests
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  // ✅ Boot 4 test-slice modules (this is the real fix)
  testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
  testImplementation("org.springframework.boot:spring-boot-starter-jdbc-test")

  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:postgresql")
  testRuntimeOnly("org.postgresql:postgresql")

  testImplementation(testFixtures(project(":test-support")))
}

tasks.test { useJUnitPlatform() }
