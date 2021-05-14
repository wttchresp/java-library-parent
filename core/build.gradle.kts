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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "core"
            from(components["java"])

            pom {
                name.set("Wttch Core")
                description.set("Wttch's Core Library")
                url.set("https://github.com/wttch96/wttch-library")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("wttch")
                        name.set("Wttch")
                        email.set("wttch@wttch.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/wttch96/wttch-library.git")
                    developerConnection.set("scm:git:ssh://github.com/wttch96/wttch-library.git")
                    url.set("http://github.com/wttch96/wttch-library/")
                }
            }
        }
    }
    repositories {
        maven {
            val releasesRepoUrl =
                uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials {
                val ossrhUsername: String by project
                val ossrhPassword: String by project
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.withType<Sign>().configureEach {
    onlyIf { !version.toString().endsWith("SNAPSHOT") }
}
