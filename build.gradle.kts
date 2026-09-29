import com.diffplug.gradle.spotless.SpotlessPlugin

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room3) apply false
    alias(libs.plugins.spotless)
}
spotless {
    kotlinGradle {
        target("*.gradle.kts", "*/*.gradle.kts")

        ktlint("1.8.0")
    }
}

subprojects {

    this.pluginManager.apply(SpotlessPlugin::class)

    spotless {

        kotlin {
            target("src/*/kotlin/**/*.kt")
            ktlint("1.8.0")
                .customRuleSets(listOf("io.nlopez.compose.rules:ktlint:0.6.4"))
        }

        kotlinGradle {
            target("*.gradle.kts", "*/*.gradle.kts")

            ktlint("1.8.0")
        }
    }
}
