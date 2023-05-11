package com.example.models

import java.util.concurrent.atomic.AtomicInteger

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
class Article
private constructor(val id: Int, var title: String, var body: String) {
    companion object {
        private val idCounter = AtomicInteger()

        /**
         * Función que crea y devuelve un nuevo objeto Article con un identificador
         * generado de forma automática a partir del título y el cuerpo de texto
         * especificados.
         * @param title  Título del nuevo artículo.
         * @param body  Cuerpo de texto del nuevo artículo.
         */
        fun newEntry(title: String, body: String) = Article(idCounter.getAndIncrement(), title, body)
    }


}

// Lista mutable de objetos de tipo Article
val articles = mutableListOf(
    Article.newEntry(
        "The drive to develop!",
        "...it's what keeps me going."
    )
)

