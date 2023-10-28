[![](https://jitpack.io/v/hoonsa-lim/android-library-ui.svg)](https://jitpack.io/#hoonsa-lim/android-library-ui)

# android-library-ui
## Modules
1. admob
2. common
3. compose 

## How to use
### dependencies
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

### admob
```kotlin
//project/build.gradle.kts
//project/build.gradle.kts
plugins {
    ...
    val google_service_version = "x.x.x"
    id("com.google.gms.google-services") version "$google_service_version" apply false
}

//initialize
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AdManager.initialize(this) {}
    }
}
```

```xml
    <!--set application to AndroidManifest.xml-->
    <application
        android:name=".MyApplication"
        ...>

        ...
    </application>
```
```xml
    <!--set admob application id to AndroidManifest.xml-->
    <application
        ...>
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-3940256099942544~3347511713"/>
        ...
    </application>
```