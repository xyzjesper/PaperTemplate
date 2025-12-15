package dev.xyzjesper.papertemplate.database.models

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object Template : Table<Nothing>("template") {
    val id = int("id")
    val name = varchar("name")
}