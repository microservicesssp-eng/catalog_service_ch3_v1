plugins {

    java
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

//extra["springCloudVersion"] = "2021.0.9"
extra["springCloudVersion"] = "2025.1.2" 
//extra["testcontainersVersion"] = "1.19.8"

dependencyManagement { 
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}



configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    systemProperty("spring.profiles.active", "testdata")
}


java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {


    implementation("org.springframework.boot:spring-boot-starter-flyway")
implementation("org.flywaydb:flyway-database-postgresql")

    // Или, если используется полноценный тестовый стартер:
  //  testImplementation("org.springframework.boot:spring-boot-starter-test")
    
//implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}"))
   implementation("org.springframework.boot:spring-boot-starter-data-jdbc")// data config
    testImplementation("org.springframework.boot:spring-boot-starter-data-jdbc-test")

    //implementation("org.flywaydb:flyway-core")

    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-starter-jackson-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
     //  kapt("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Source: https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-config
implementation("org.springframework.cloud:spring-cloud-starter-config")

     implementation("org.springframework.retry:spring-retry")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    //testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:testcontainers-postgresql")
}

// dependencyManagement {
//     imports {
//         mavenBom("org.testcontainers:testcontainers-bom:${extra["testcontainersVersion"]}")
//     }
// }

tasks.withType<Test> {
	useJUnitPlatform()
}
