plugins {
    java
    id(Plugins.spotless) version Versions.Plugin.spotless
}

group = "com.wttch"
version = "1.0.0-release"

repositories {
    mavenCentral()
    maven {
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    implementation("com.wttch:core:0.1-SNAPSHOT")
}

subprojects {
    apply {
        plugin(Plugins.spotless)
        plugin("java")
    }

    spotless {
        java {
            // targetExclude("**/cn/techrecycle/rms/dao/entity/**/*.*")
            googleJavaFormat()
            // optional: you can specify a specific version and/or switch to AOSP style
            // googleJavaFormat('1.7').aosp()
        }
    }
}