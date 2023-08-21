import org.apache.tools.ant.util.JavaEnvUtils.VERSION_1_8

plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.dagger.hilt.android")
	id("com.google.devtools.ksp") version "1.8.10-1.0.9"
	id("kotlin-kapt")
}
object DependencyVers {
	val compose_ui_version = "1.3.3"
	val destinations_version = "1.7.41-beta"
	val retrofit_version = "2.9.0"
	val hilt_version = "2.44"
	val paging_version = "3.1.1"
}

android {
	namespace = "ru.sakhno.gallery"
	compileSdk = 33
	
	defaultConfig {
		applicationId = "ru.sakhno.gallery"
		minSdk = 24
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	
	buildFeatures {
		compose = true
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
		
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.4.3"
	}
	kotlinOptions {
		jvmTarget = "17"
	}
}

dependencies {
	implementation("com.squareup.retrofit2:retrofit:${DependencyVers.retrofit_version}")
	implementation("com.squareup.retrofit2:converter-gson:${DependencyVers.retrofit_version}")
	implementation("io.github.raamcosta.compose-destinations:core:${DependencyVers.destinations_version}")
	ksp("io.github.raamcosta.compose-destinations:ksp:${DependencyVers.destinations_version}")
	
	implementation ("androidx.paging:paging-runtime:${DependencyVers.paging_version}")
	implementation ("androidx.paging:paging-compose:1.0.0-alpha18")
	implementation("com.google.dagger:hilt-android:${DependencyVers.hilt_version}")
	kapt("com.google.dagger:hilt-compiler:${DependencyVers.hilt_version}")
	implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
	
	implementation("androidx.compose.ui:ui:${DependencyVers.compose_ui_version}")
	implementation("androidx.compose.ui:ui-tooling-preview:${DependencyVers.compose_ui_version}")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
	implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
	implementation("androidx.compose.material:material:1.3.1")
	implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
	
	implementation("com.github.bumptech.glide:compose:1.0.0-alpha.1")
	
	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation("androidx.compose.ui:ui-test-junit4:${DependencyVers.compose_ui_version}")
	debugImplementation("androidx.compose.ui:ui-tooling:${DependencyVers.compose_ui_version}")
	debugImplementation("androidx.compose.ui:ui-test-manifest:${DependencyVers.compose_ui_version}")
}