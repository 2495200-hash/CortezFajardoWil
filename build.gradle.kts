plugins {
    // Plugin principal de aplicaciones Android
    alias(libs.plugins.android.application) apply false

    // Plugin oficial para Jetpack Compose
    alias(libs.plugins.kotlin.compose) apply false

    // Plugin utilizado por Room para generar código automáticamente
    alias(libs.plugins.ksp) apply false
}