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
		maven {
			url = uri("https://maven.jzy3d.org/releases")
		}
		maven {
			url = uri("https://maven.jzy3d.org/snapshots")
		}
	}
}

subprojects {
	apply(plugin = "application")

	dependencies {
		implementation("org.moeaframework:moeaframework:4.0")
		implementation("org.jfree:jfreesvg:3.4.3")

		implementation("org.jzy3d:jzy3d-everything:2.1.1-SNAPSHOT")
	}

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
		options.compilerArgs.addAll(listOf("--release", "17"))
	}
}

