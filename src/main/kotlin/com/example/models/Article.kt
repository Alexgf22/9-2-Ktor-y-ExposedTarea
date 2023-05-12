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

/**
 * Clase object llamada: Articles que extiende la clase Table.
 * Esto indica que la clase representa una tabla en una base de datos relacional.
 */
object Articles: Table() {
    /*
     Define una columnade tipo entero, que se autoincrementa en cada registro
     que se inserta en la tabla
     */
    val id = integer("id").autoIncrement()

    // Columna de tipo varchar con una longitud max. de 128 caracteres
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    // En este caso define la clave primaria de la tabla Articles
    override val primaryKey = PrimaryKey(id)
}