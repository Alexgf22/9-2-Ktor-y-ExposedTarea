package com.example.dao

import com.example.models.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

/**
 * En esta clase objeto DatabaseFactory, se configura la base de datos H2 en el
 * init y crea la tabla "Articles" mediante SchemaUtils de la biblioteca "Exposed".
 */
object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        // Esta de abajo es la segunda opción de url
        //val jdbcURL = "jdbc:h2:./default"
        val user = "user"
        val password = "user"
        val database = Database.connect(jdbcURL, driverClassName, user,password)
        transaction(database) {
            SchemaUtils.create(Articles)
            SchemaUtils.create(Entities)
        }
    }

    /**
     * Este método suspendido usa la función de orden superior
     * newSuspendedTransaction para ejecutar de forma segura las consultas en
     * la base de datos.
     */
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}