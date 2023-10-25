[![](https://jitpack.io/v/hoonsa-lim/android-library-ui.svg)](https://jitpack.io/#hoonsa-lim/android-library-ui)

# android-library-ui
## Modules
1. common
2. compose

## How to use
```kotlin
//settings.gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url = uri("https://www.jitpack.io") }
    }
}

//app/build.gradle
dependencies {
    ...
    val version = "latest release tag"
    val module = "module name"
    implementation("com.github.hoonsa-lim.android-library-ui:$module:$version")
    
    //ex) implementation("com.github.hoonsa-lim.android-library-ui:compose:1.0.0")
}

```
