package com.example.models


import org.jetbrains.exposed.sql.*

/**
 * La clase Article representa un artículo con un identificador único, un título y un cuerpo de texto.
 * @property id: Int  identificador único del artículo
 * @property title: String  título del artículo
 * @property body: String   cuerpo de texto del artículo
 * @companionObject objeto compañero que permite la creación de nuevos objetos Article con
 * un identificador que se genera automáticamente.
 * La variable idCounter es un contador atómico que se usa para generar
 * identificadores únicos para cada artículo.
 */
data class Article(val id: Int, val title: String, val body: String)

object Articles: Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    override val primaryKey = PrimaryKey(id)
}