plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	`maven-publish`
	`java-library`
}

group = "dev.trustproject"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
	publications {
		create<MavenPublication>("default") {
			from(components["java"])
			// Include any other artifacts here, like javadocs
		}
	}

	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/primatime/spring-vocdoni")
			credentials {
				username = providers.environmentVariable("GITHUB_ACTOR")
						.orElse(providers.gradleProperty("github.username")).get()
				password = providers.environmentVariable("GITHUB_TOKEN")
						.orElse(providers.gradleProperty("github.password")).get()
			}
		}
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.google.protobuf:protobuf-java:3.21.12")
	implementation("org.web3j:crypto:4.10.1") // 5.0.0 is old version from 5/2020
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName("bootRun").enabled = false
tasks.getByName("bootJar").enabled = false
