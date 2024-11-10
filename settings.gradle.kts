rootProject.name = "flash-homework"

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  @Suppress("UnstableApiUsage")
  repositories {
    mavenCentral()
  }
}

include(":app")
//include(":modules:sanitization")