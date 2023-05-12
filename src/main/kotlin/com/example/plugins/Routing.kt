package com.example.plugins

import com.example.models.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import com.example.dao.DAOFacadeImpl
import com.example.dao.dao


/**
 * Esta función configura las rutas utilizadas por la app, incluyendo la redirección de la
 * ruta principal a la lista de artículos, el manejo de la creación, actualización y eliminación de artículos
 * y la visualización de un artículo específico.
 */

fun Application.configureRouting() {
    routing {// Función de enrutamiento que configura las rutas de la app



        // redirige todas GET las solicitudes realizadas a la /ruta a /articles


        get("/") {
            call.respondRedirect("articles")
        }

        route("articles") {



            /*
            FreeMarkerContent() , objeto que representa el contenido para ser enviado al cliente.
            Acepta dos parámetros: template: nombre de la plantilla, model: le pasamos una lista de archivos

             */

            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to dao.allArticles())))
                }

            get("new") {
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }

            // Crea un nuevo artículo a partir de los parámetros enviados en el formulario.
            post {
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val article = dao.addNewArticle(title, body)
                call.respondRedirect("/articles/${article?.id}")
            }

            // Para mostrar el contenido de un artículo específico, se usa el ID del artículo como parámetro de ruta
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("show.ftl", mapOf("article" to dao.article(id))))
            }



             /*Ruta para editar un artículo. ('call.parameters') se usa para obtener el identificador del artículo
               y encontrar este artículo en un almacén
              */
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to dao.article(id))))
            }



            /*En primer lugar, con call.parameters, obtenemos el ID del artículo a editar.
            Con call.receiveParameters se usa para que un usuario inicie la acción (update o delete)
            Dependiendo de la acción, el artículo se actualiza o elimina del almacenamiento.
             */
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        dao.editArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        dao.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
    }
}




