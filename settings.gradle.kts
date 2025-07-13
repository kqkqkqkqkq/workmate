pluginManagement {
    repositories {
        google()
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

rootProject.name = "workmate"
include(":app")
include(":di")
include(":ui-kit")
include(":navigation")
include(":core:characters-database")
include(":core:characters-api")
include(":core:characters-data")
include(":feature:main:ui")
include(":feature:main:ui-logic")
include(":feature:detail:ui")
include(":feature:detail:ui-logic")
include(":common:ui-models")
