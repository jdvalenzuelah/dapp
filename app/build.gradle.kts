import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val tinyLogVersion = "2.3.0-M1"

plugins {
    application
    kotlin("jvm") version "1.4.0"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
    jcenter()
}

group = "com.rombit"
version = "1.0.0"

application {
    mainClassName = "com.rombit.MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.web3j:core:4.8.4")

    implementation("com.github.kwebio:kweb-core:0.8.6")
    implementation("com.github.ajalt.clikt:clikt:3.1.0")

    implementation("org.http4k:http4k-core:4.8.0.0")
    implementation("org.http4k:http4k-server-apache4:4.8.0.0")
    implementation("org.http4k:http4k-format-jackson:4.8.0.0")

    implementation("org.tinylog:tinylog-api-kotlin:$tinyLogVersion")
    implementation("org.tinylog:tinylog-impl:$tinyLogVersion")
    implementation("org.tinylog:slf4j-tinylog:$tinyLogVersion")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnitPlatform {
        includeEngines("junit-jupiter","spek2")
    }

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events("passed", "failed", "skipped")
    }
}

tasks.withType<KotlinCompile> {

    sourceCompatibility = "11"
    targetCompatibility = "11"

    kotlinOptions {
        jvmTarget = "11"
        apiVersion = "1.4"
        languageVersion = "1.4"
    }
}

tasks.wrapper {
    gradleVersion = "6.6.1"
}
