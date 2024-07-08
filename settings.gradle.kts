pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven {
            url = uri("https://www.jitpack.io")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven {
            url = uri("https://www.jitpack.io")
        }
        mavenCentral()
    }
}

rootProject.name = "NewsProjectSCG"
include(":app")
include(":data")
include(":core")
include(":features:home")
include(":features:detail")
