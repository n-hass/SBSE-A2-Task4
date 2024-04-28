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
	id("io.freefair.lombok") version "8.6"
}

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "application")

	dependencies {
		implementation("org.moeaframework:moeaframework:4.0")
		implementation("org.jfree:jfreesvg:3.4.3")
	}

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
		if (JavaVersion.current().isJava9Compatible) {
			options.compilerArgs.addAll(listOf("--release", "17"))
		}
	}
}

