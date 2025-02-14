*****Dependencies*****
1. buildFeatures {
        dataBinding = true
        viewBinding = true

    }

*** databinding still requires kapt




2. add kapt
To top level build.gradle.kts file.
plugins {
id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}


To module-level build.gradle.kts file
plugins{
id("com.google.devtools.ksp")
}

dependencies {

//room - 2.6.1
ksp("androidx.room:room-compiler") //shows warning
implementation(libs.androidx.room.runtime)
annotationProcessor(libs.androidx.room.compiler)
implementation(libs.androidx.room.ktx)

//viewmodel with livedata -2.8.7
implementation(libs.androidx.lifecycle.viewmodel.ktx)
implementation(libs.androidx.lifecycle.livedata.ktx)
implementation(libs.androidx.lifecycle.runtime.ktx)
implementation(libs.core.ktx)

}

3. Add safeArgs
To top level build.gradle file
def nav_version = "2.3.0"
classpath “androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version”
//OR
buildscript {
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

To app level build.gradle file
apply plugin(androidx.navigation.safeargs.kotlin)
//OR
id("androidx.navigation.safeargs.kotlin")



*****Steps***** 
1. Setup themes
2. Setup required dependencies
3. Setup directories
	Model -> data		-> room
		repository	retrofit
				model
	
	view ->activity
		fragment
		adapter

	viewmodel ->viewmodel
		viewmodelfactory

4. Decide model based on requirements
5. Setup room database -> add model entity
			add dao
			add database file
			Converters if required
6. Create repository and communicate with database
7. Setting up viewModel and viewModelProviderFactory
8. Make models serialisable
9. Design UI - with navhosts also handle arugements
10. Communicate with viewmodel and display all the required data.
