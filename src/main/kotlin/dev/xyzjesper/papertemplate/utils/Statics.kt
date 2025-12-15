package dev.xyzjesper.papertemplate.utils

import dev.xyzjesper.papertemplate.Main
import dev.xyzjesper.papertemplate.config.ConfigManager
import dev.xyzjesper.papertemplate.database.DatabaseManager
import io.github.cdimascio.dotenv.dotenv
import java.io.File

object Statics {
    
    fun load() {
        // Configs
        ConfigManager.load()
        
        try {
            Main.instance.dotEnv = dotenv(block = {
                directory = "plugins/${Main.instance.name}/"
            })
        } catch (e: Exception) {
            File("plugins/${Main.instance.name}/.env").createNewFile()
        }

        // Database
        DatabaseManager.init()
        DatabaseManager.preload()
    }
    
}