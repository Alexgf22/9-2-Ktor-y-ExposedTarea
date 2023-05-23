package com.example

import com.example.dao.DatabaseFactory
import com.example.plugins.configureRouting
import com.example.plugins.configureTemplating
import io.ktor.server.application.*
import io.ktor.server.netty.*


fun main(args: Array<String>): Unit =
    EngineMain.main(args)


/**
 * La función Application.module() es el módulo de la app que se usa para configurar
 * el servidor web. En esta función, se llaman a otras funciones de configuración que se encargan
 * de establecer los aspectos específicos del servidor, como la configuración de las plantillas,
 * la serialización, las bases de datos y las rutas. Para llevar a cabo la configuración incluye
 * las siguientes funciones: DatabaseFactory.init(), configureRouting() y configureTemplating()
 *
 */
@Suppress("unused")
fun Application.module() {
    DatabaseFactory.init()
    configureRouting()
    configureTemplating()
}
