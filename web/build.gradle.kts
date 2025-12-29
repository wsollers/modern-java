plugins {
  `java-library`
}

dependencies {
  api(platform("org.springframework.boot:spring-boot-dependencies:3.2.12"))

  api(project(":domain"))
  implementation(project(":utilities"))

  api("org.springframework.boot:spring-boot-starter-web")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}
