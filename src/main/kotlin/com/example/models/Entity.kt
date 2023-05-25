package com.example.models


import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Entity(
    val id: Int,
    val value: String,
    val name: String,
    val description: String,
    val sectionId: String,
    val order: Int,
    val idArticle: Int)


object Entities: Table() {
    val id = integer("id").autoIncrement()
    val value = varchar("value", 1024)
    val name = varchar("name", 128)
    val description = varchar("description", 256)
    val sectionId = varchar("sectionId", 32)
    val order = integer("order")
    val idArticle = integer("idArticle").references(Articles.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)
}