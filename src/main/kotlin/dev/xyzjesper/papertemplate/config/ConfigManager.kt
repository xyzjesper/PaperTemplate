package dev.xyzjesper.papertemplate.config

import dev.xyzjesper.papertemplate.Main
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/${Main.instance.name}/config.json")

    var settings = settingsFile.loadConfig(SettingsData(
        template = ""
    ))

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
    }

    fun load() {
        settings
        save()
    }

    fun reload() {
        settings = loadFromFile(settingsFile)
    }
    
}
