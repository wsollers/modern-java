plugins {
  `java-library`
}

dependencies {
  // Import Spring Boot BOM (this supplies versions)
  api(platform("org.springframework.boot:spring-boot-dependencies:3.2.12"))

  api("jakarta.persistence:jakarta.persistence-api")
  api("jakarta.validation:jakarta.validation-api")

  api("com.fasterxml.jackson.core:jackson-annotations")
  api("com.fasterxml.jackson.core:jackson-databind")

  implementation(project(":utilities"))     // provides dev.wsollers.logging.LogFactory (if it's there)
  implementation("org.slf4j:slf4j-api")     // provides org.slf4j.Logger



}
