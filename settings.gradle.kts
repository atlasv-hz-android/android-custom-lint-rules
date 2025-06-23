@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AtlasXV/android-libs")
            credentials {
                username = System.getenv("GPR_USR") ?: extra["GPR_USR"]?.toString()
                password = System.getenv("GPR_KEY") ?: extra["GPR_KEY"]?.toString()
            }
            content {
                includeGroupByRegex("com\\.atlasv.*")
                includeGroupByRegex("com\\.android\\.now.*")
            }
        }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AtlasXV/android-libs")
            credentials {
                username = System.getenv("GPR_USR") ?: extra["GPR_USR"]?.toString()
                password = System.getenv("GPR_KEY") ?: extra["GPR_KEY"]?.toString()
            }
            content {
                includeGroupByRegex("com\\.atlasv.*")
                includeGroupByRegex("com\\.android\\.now.*")
            }
        }
        google()
        mavenCentral()
        mavenLocal()
    }
}

include(":app")
include(":checks")
include(":library")
