plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.openapi.generator") version "7.0.1"
	id("com.diffplug.spotless") version "6.19.0"
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

openApiGenerate {
	validateSpec.set(false)
	generatorName.set("java")
	inputSpec.set("$rootDir/vocdoni-api.yaml")
	outputDir.set("$buildDir/generated")
	apiPackage.set("io.vocdoni.api")
	modelPackage.set("io.vocdoni.model")
	invokerPackage.set("io.vocdoni.invoker")
	configOptions.put("dateLibrary", "java8")
}

spotless {
	java {
		target("src/*/java/dev/trustproject/vocdoni/**/*.java")
		toggleOffOn()
		palantirJavaFormat()
		removeUnusedImports()
		trimTrailingWhitespace()
		endWithNewline()
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
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("io.swagger:swagger-annotations:1.6.8")
	implementation("com.google.code.findbugs:jsr305:3.0.2")
	implementation("com.squareup.okhttp3:okhttp:4.10.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
	implementation("com.google.code.gson:gson:2.9.1")
	implementation("io.gsonfire:gson-fire:1.8.5")
	implementation("javax.ws.rs:jsr311-api:1.1.1")
	implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("jakarta.annotation:jakarta.annotation-api:1.3.5")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.google.protobuf:protobuf-java:3.21.12")
	implementation("org.web3j:crypto:4.10.3") // 5.0.0 is old version from 5/2020
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName("bootRun").enabled = false
tasks.getByName("bootJar").enabled = false
