pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TradernetTestApp"
include(":app")
include(":domain")
include(":feature-quote-list")
include(":screen-quote-list")
include(":base-ui-kit")
include(":data-remote")
include(":data-remote-impl")
include(":base-network")
include(":presentation")
