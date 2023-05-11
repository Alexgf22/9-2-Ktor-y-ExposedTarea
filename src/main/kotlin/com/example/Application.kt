package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

/**
 * En esta función se inicia el servidor web. Con la función embeddedServer()
 * se crea un servidor web embebido en Netty. El puerto '8080' y la ip por defecto.
 *  Además se especifica el módulo de la app que se usará para
 *  configurar el servidor, que es la función Application.module.
 *  Por último, se llama al método start para iniciar el servidor y esperar a que
 *  finalice la ejecución.
 */
fun main() {
    embeddedServer(Netty, port =8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


/**
 * La función Application.module() es el módulo de la app que se usa para configurar
 * el servidor web. En esta función, se llaman a otras funciones de configuración que se encargan
 * de establecer los aspectos específicos del servidor, como la configuración de las plantillas,
 * la serialización, las bases de datos y las rutas. Para llevar a cabo la configuración incluye
 * las siguientes funciones: configureTemplating(), configureSerialization(), configureDatabases()
 * y configureRouting()
 *
 */
fun Application.module() {
    configureTemplating()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
