plugins {
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization") version "2.+"
    id("com.gradleup.shadow") version "9.+"
    id("xyz.jpenilla.run-paper") version "3.+"
    id("de.eldoria.plugin-yml.paper") version "0.8.+"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("maven-publish")
}

val mcVersion = properties["minecraftVersion"] as String

val projectVersion = properties["version"] as String
val projectName = properties["name"] as String
val projectDescription = properties["description"] as String

val groupID = properties["group"] as String
val mainClass = properties["main"] as String


val twilightVersion = properties["twilightVersion"] as String
val commandAPIVersion = properties["commandAPIVersion"] as String
val rethisVersion = properties["rethisVersion"] as String
val kormVersion = properties["kormVersion"] as String
val dotenvVersion = properties["dotenvVersion"] as String
val crystalShardVersion = properties["crystalShardVersion"] as String

group = groupID
version = projectVersion

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.flyte.gg/releases")
    maven {
        name = "xyzReleases"
        url = uri("https://repo.xyzhub.link/releases")
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Database
    implementation("eu.vendeli:rethis:$rethisVersion")
    implementation("org.ktorm:ktorm-core:$kormVersion")

    // ENV
    implementation("io.github.cdimascio:dotenv-kotlin:$dotenvVersion")
    
    // CrystalShard
    implementation("net.crystopia:crystalshard:$crystalShardVersion")
    
    // Paper
    // compileOnly("io.papermc.paper:paper-api:${mcVersion}-R0.1-SNAPSHOT")
    paperweight.paperDevBundle("$mcVersion-R0.1-SNAPSHOT")
    // Twilight
    implementation("gg.flyte:twilight:${twilightVersion}")

    // Command API
    compileOnly("dev.jorel:commandapi-paper-core:${commandAPIVersion}")
    implementation("dev.jorel:commandapi-paper-shade:${commandAPIVersion}")
    implementation("dev.jorel:commandapi-kotlin-paper:${commandAPIVersion}")
}

kotlin {
    jvmToolchain(24)
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
    version = mcVersion
    description = projectDescription
    main = mainClass
    authors = listOf("xyzjesper", "xyzPlugins")
    apiVersion = "1.21"
}

publishing {
    repositories {
        maven {
            name = "Reposilite"
            url = uri("https://repo.xyzhub.link/releases")
            credentials {
                username = System.getenv("REPOSILITE_USER") ?: System.getProperty("REPOSILITE_USER") ?: "USERNAME"
                password = System.getenv("REPOSILITE_TOKEN") ?: System.getProperty("REPOSILITE_TOKEN") ?: "TOKEN"
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("reposilite") {
            from(components["java"])
            artifactId = projectName
            groupId = group as String
            version = version
        }
    }
}