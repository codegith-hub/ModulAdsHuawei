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
        maven {
            url = uri("https://artifact.bytedance.com/repository/pangle/")
        }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://developer.huawei.com/repo/") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://artifact.bytedance.com/repository/pangle/")
        }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://developer.huawei.com/repo/") }

    }
}

rootProject.name = "ModulAdsHuawei"
include(":app")
include(":Adsmodul")
