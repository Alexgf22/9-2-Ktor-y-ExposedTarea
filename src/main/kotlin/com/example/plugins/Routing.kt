package com.example.plugins

import com.example.dao.dao
import com.example.dao.daoEntity
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*


/**
 * Esta función configura las rutas utilizadas por la app, incluyendo la redirección de la
 * ruta principal a la lista de artículos, el manejo de la creación, actualización y eliminación de artículos
 * y la visualización de un artículo específico.
 */

fun Application.configureRouting() {
    routing {// Función de enrutamiento que configura las rutas de la app



        // redirige todas GET las solicitudes realizadas a la /ruta a /articles


        get("/") {
            call.respondRedirect("entities")
        }

        route("articles") {



            /*
            FreeMarkerContent() , objeto que representa el contenido para ser enviado al cliente.
            Acepta dos parámetros: template: nombre de la plantilla, model: le pasamos una lista de archivos

             */

            get {
                call.respond(FreeMarkerContent("indexArticle.ftl", mapOf("articles" to dao.allArticles())))
                }

            get("new") {
                call.respond(FreeMarkerContent("newArticle.ftl", model = null))
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
                call.respond(FreeMarkerContent("showArticle.ftl", mapOf("article" to dao.article(id))))
            }



             /*Ruta para editar un artículo. ('call.parameters') se usa para obtener el identificador del artículo
               y encontrar este artículo en un almacén
              */
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("editArticle.ftl", mapOf("article" to dao.article(id))))
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



        // Entidad Campo


        route("entities") {

            /*
            FreeMarkerContent() , objeto que representa el contenido para ser enviado al cliente.
            Acepta dos parámetros: template: nombre de la plantilla, model: le pasamos una lista de archivos
             */
            get {
                call.respond(FreeMarkerContent("indexEntity.ftl", mapOf("entities" to daoEntity.allEntities())))
            }

            get("newEntity") {
                call.respond(FreeMarkerContent("newEntity.ftl", mapOf("articles" to dao.allArticles())))
            }

            // Crea un nuevo item a partir de los parámetros enviados en el formulario.
            post {
                val formParameters = call.receiveParameters()
                val value = formParameters.getOrFail("value")
                val name = formParameters.getOrFail("name")
                val description = formParameters.getOrFail("description")
                val sectionId = formParameters.getOrFail("sectionId")
                val order = formParameters.getOrFail<Int>("order").toInt()
                val idArticle = formParameters.getOrFail("idArticle").toInt()
                val entity = daoEntity.addNewEntity(value, name, description, sectionId, order, idArticle)
                call.respondRedirect("/entities/${entity?.id}")
            }

            // Para mostrar el contenido de un item específico, se usa el ID del item como parámetro de ruta
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id")
                call.respond(FreeMarkerContent("showEntity.ftl", mapOf("entity" to daoEntity.entity(id))))
            }
            /* Ruta para editar un item. ('call.parameters') se usa para obtener el identificador del item
             y encontrar este item en un almacén
             */
            get("{id}/editEntity") {
                val id = call.parameters.getOrFail<Int>("id")
                call.respond(FreeMarkerContent("editEntity.ftl", mapOf("entity" to daoEntity.entity(id))))
            }

            /*
            En primer lugar, con call.parameters, obtenemos el ID del item a editar.
            Con call.receiveParameters se usa para que un usuario inicie la acción (update o delete)
            Dependiendo de la acción, el item se actualiza o elimina del almacenamiento.
             */
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id")
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val value = formParameters.getOrFail("value")
                        val name = formParameters.getOrFail("name")
                        val description = formParameters.getOrFail("description")
                        val sectionId = formParameters.getOrFail("sectionId")
                        val order = formParameters.getOrFail<Int>("order").toInt()
                        val idArticle = formParameters.getOrFail("idArticle").toInt()
                        daoEntity.editEntity(id, value, name, description, sectionId, order, idArticle)
                        call.respondRedirect("/entities/$id")
                    }
                    "delete" -> {
                        daoEntity.deleteEntity(id)
                        call.respondRedirect("/entities")
                    }
                }
            }
        }






    }
}




