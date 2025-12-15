package dev.xyzjesper.papertemplate

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import dev.xyzjesper.papertemplate.config.ConfigManager
import dev.xyzjesper.papertemplate.database.DatabaseManager
import dev.xyzjesper.papertemplate.utils.Statics
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import net.crystopia.crystalshard.utils.Log
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    init {
        instance = this
    }

    lateinit var dotEnv: Dotenv
    
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIPaperConfig(this).silentLogs(true))

        Statics.load()

        Log.info("Loading Plugin...")
    }

    override fun onEnable() {
        CommandAPI.onEnable()

        Log.info("Plugin enabled!")
    }

    override fun onDisable() {
        CommandAPI.onDisable()

        Log.info("Plugin disabled!")
    }

}