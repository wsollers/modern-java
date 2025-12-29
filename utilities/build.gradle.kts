plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 3.2.x manages commons-text version (and a lot more)
    api(platform("org.springframework.boot:spring-boot-dependencies:3.2.12"))


    api("org.slf4j:slf4j-api")
}
