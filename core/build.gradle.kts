plugins {
    `java-library`
    `maven-publish`
    signing
}

group = "com.wttch"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "core"
            from(components["java"])

            pom(Publishing.pom)
        }
    }
    repositories(Publishing.repositories(project))
}

signing {
    // useInMemoryPgpKeys("", "")
    sign(publishing.publications["mavenJava"])
}

tasks.withType<Sign>().configureEach {
    onlyIf { isReleaseVersion() }
}
