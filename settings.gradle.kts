pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io") // ✅ For Barteksc PDF Viewer
        maven(url = "https://jcenter.bintray.com")
       maven (url = "https://repository.liferay.com/nexus/content/repositories/public/")// ⚠️ Only if needed
    }
}

rootProject.name = "Lab"
include(":app")
