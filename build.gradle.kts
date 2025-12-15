plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
    id("com.gradleup.shadow") version "9.0.3"
    id("xyz.jpenilla.run-paper") version "3.+"
    id("de.eldoria.plugin-yml.paper") version "0.8.0"
}

val mcVersion = properties["minecraftVerions"] as String

val projectVersion = properties["version"] as String
val projectName = properties["name"] as String
val projectDescription = properties["description"] as String

val groupID = properties["group"] as String
val mainClass = properties["main"] as String


val twilightVersion = properties["twilightVersion"] as String
val commandAPIVersion = properties["commandAPIVersion"] as String
val rethisVersion = properties["rethisVersion"] as String
val kormVersion = properties["rethisVersion"] as String

group = groupID
version = projectVersion

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.flyte.gg/releases")
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Database
    implementation("eu.vendeli:rethis:$rethisVersion")
    implementation("org.ktorm:ktorm-core:$kormVersion")


    // Paper
    compileOnly("io.papermc.paper:paper-api:${mcVersion}-R0.1-SNAPSHOT")

    // Twilight
    implementation("gg.flyte:twilight:${twilightVersion}")

    // Command API
    compileOnly("dev.jorel:commandapi-bukkit-core:${commandAPIVersion}")
    implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:${commandAPIVersion}")
    implementation("dev.jorel:commandapi-bukkit-kotlin:${commandAPIVersion}")
}

kotlin {
    jvmToolchain(21)
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks {
    runServer {
        minecraftVersion(mcVersion)
    }
}

paper {
    name = projectName
    version = version
    description = projectDescription
    main = mainClass
    authors = listOf("xyzjesper")
    apiVersion = "1.19"
}
