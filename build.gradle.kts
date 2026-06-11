import java.security.MessageDigest

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

val stableBuildId = MessageDigest.getInstance("SHA-256")
    .digest(rootDir.absolutePath.toByteArray())
    .joinToString("") { "%02x".format(it) }
    .take(12)

val externalBuildRoot = providers.gradleProperty("sit305.buildDir")
    .orElse(providers.environmentVariable("SIT305_BUILD_DIR"))
    .orElse(
        providers.systemProperty("java.io.tmpdir").map { tempDir ->
            file(tempDir).resolve("SIT305-3-1C-build/$stableBuildId").absolutePath
        }
    )
    .get()

allprojects {
    val projectBuildFolder = if (path == ":") "root" else path.removePrefix(":").replace(':', '-')

    // Keep generated Gradle/Android files out of the project tree. This avoids Windows OneDrive
    // locking files under app/build/intermediates, which can make Android resource merging fail
    // with java.nio.file.AccessDeniedException when Gradle tries to clean stale outputs.
    layout.buildDirectory.set(file(externalBuildRoot).resolve(projectBuildFolder))
}
