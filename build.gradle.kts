// plugins {
//     id 'java'
// }

// allprojects {
//     repositories {
//         mavenCentral()
//     }
// }

// subprojects {
//     apply plugin: 'application'

//     dependencies {
//         implementation 'org.moeaframework:moeaframework:4.0'
//         implementation 'org.jfree:jfreesvg:5.0.2'
//     }

//     tasks.withType(JavaCompile) {
//         options.encoding = 'UTF-8'
//     }

//     compileJava {
//         if (JavaVersion.current().isJava9Compatible()) {
//             options.compilerArgs.addAll(['--release', '8']) 
//         }
//     }
// }

plugins {
    java
    application
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

subprojects {
    apply(plugin = "application")

    dependencies {
        implementation("org.moeaframework:moeaframework:4.0") // Verify version
        implementation("org.jfree:jfreesvg:3.4.3") // Verify version
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        if (JavaVersion.current().isJava9Compatible) {
            options.compilerArgs.addAll(listOf("--release", "8"))
        }
    }
}

