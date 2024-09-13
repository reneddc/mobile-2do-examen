plugins {
    alias(libs.plugins.ucb.android.library)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.calyr.network"

}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    kapt(libs.moshi.kapt)
}

kapt {
    correctErrorTypes = true
}