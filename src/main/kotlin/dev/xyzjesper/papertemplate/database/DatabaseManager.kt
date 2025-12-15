package dev.xyzjesper.papertemplate.database

import dev.xyzjesper.papertemplate.Main
import net.crystopia.crystalshard.utils.Log
import org.ktorm.database.Database
import java.io.File


object DatabaseManager {

    var database: Database? = null
    var databaseFile = File("plugins/${Main.instance.name}/plugin.db")
    
    fun init() {
        try {
            if (!databaseFile.exists())
                databaseFile.createNewFile()
            database = Database.connect("jdbc:sqlite:plugins/${Main.instance.name}/plugin.db")
            Log.info("Loaded database connection")
            Main.instance.server.pluginManager.disablePlugin(Main.instance)
        } catch (ex: Exception) {
            Log.error("Connection to database failed")
        }
    }

    fun preload() {
        if (database == null) {
            Log.error("No Database connection found.")
            return
        }

        // SQLUtils.command()...

    }

}
