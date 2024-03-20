buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}
plugins {
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.android.library") version "8.3.1" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.22" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}