plugins {
  `java-library`
  id("io.spring.dependency-management")
  id("io.freefair.lombok") version "8.11" // or latest you prefer
}

dependencies {

  // Repositories live here, so Spring Data JPA must be on MAIN compile classpath
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")


  api("com.fasterxml.jackson.core:jackson-annotations")
  api("com.fasterxml.jackson.core:jackson-databind")

  implementation(project(":utilities"))     // provides dev.wsollers.logging.LogFactory (if it's there)
  implementation("org.slf4j:slf4j-api")     // provides org.slf4j.Logger

  // Postgres driver for test runtime
  testRuntimeOnly("org.postgresql:postgresql")

  // JUnit 5
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:postgresql")
  //testImplementation("org.flywaydb:flyway-core")
  testImplementation(testFixtures(project(":test-support")))
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
}

tasks.test {
  useJUnitPlatform()
}