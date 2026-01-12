plugins {
  `java-library`
}

dependencies {


  api(project(":domain"))
  implementation(project(":utilities"))

  api("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  runtimeOnly("org.postgresql:postgresql")

  // optional but common:
  implementation("org.springframework.boot:spring-boot-starter-validation")

  testImplementation(testFixtures(project(":test-support")))

  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

}
