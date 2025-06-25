import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.kotlin.dsl.withType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.versionscheck)
    id("org.sonarqube") version libs.versions.sonarqube.get()
}
buildscript {
    dependencies {
        classpath(libs.publishlib)
    }
}

// 检测依赖更新 https://github.com/ben-manes/gradle-versions-plugin
// ./gradlew dependencyUpdates
// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

fun Project.getEnv(key: String): String {
    return System.getenv(key) ?: project.findProperty(key)?.toString() ?: error("需要配置环境变量或Gradle Property: $key")
}

sonar {
    properties {
        property("sonar.projectKey", project.name)
        property("sonar.projectName", project.name)
        property("sonar.host.url", getEnv("SONAR_HOST_URL"))
        property("sonar.token", getEnv("SONAR_TOKEN"))
        property("sonar.androidLint.reportPaths", "app/build/reports/lint-results-debug.xml,checks/build/reports/lint-results.xml")
    }
}