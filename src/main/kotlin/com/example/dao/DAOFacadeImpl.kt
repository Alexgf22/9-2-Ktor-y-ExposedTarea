package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Article
import com.example.models.Articles
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

/**
 * Esta clase implementa las funciones de la interfaz DAOFacade para poder añadir artículo,
 * eliminar artículo, editar artículo, devolver todos los artículos...
 */
class DAOFacadeImpl : DAOFacade {

    /**
     * @param row: ResultRow  fila de resultados  que es devuelta por una consulta a la
     * base de datos.
     * Al utilizar por ejemplo:  'row[Articles.id]'  lo que hace es acceder al valor de
     * la columna id de la tabla Articles y lo mismo con las otras variables.
     * Por lo que contruye el objeto Article con los 3 valores de las columnas
     * id, title y body  correspondientes.
     * @return Article  retorna un objeto Article con los valores de la fila.
     */
    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body]
    )


    /**
     * Esta función a través de la biblioteca de mapeo objeto-relacional "Exposed"
     * para ejecutar una consulta segura en la tabla "Articles". La consulta
     * selecciona todas las filas de la tabla y después se aplica una función map
     * a los resultados de la consulta para convertir cada fila de resultados en
     * un objeto Article con la función resultRowToArticle.
     * @return List<Article>  retorna una lista no mutable de objetos Article.
     *
     */
    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }





    /**
     * @param id: Int   se le pasa un identificador único por parámetro a la función.
     * La función article es una función suspend que usa la función de orden superior
     * dbQuery para ejecutar la consulta de manera segura en la base de datos.
     *
     * La consulta selecciona una fila específica de la tabla "Articles" usando el
     * método select. La función usa el operador eq para comparar el valor de la
     * columna "id" de la tabla "Articles" con el argumento id.
     *
     * Después, se convierte cada fila de resultados en un objeto Article con
     * la función resultRowToArticle y el método map de la clase Iterable.
     *
     * @return Article?  retorna el único objeto Article de la colección con el método singleOrNull
     * el cual retorna null si la colección está vacía o si contiene más de un elemento.
     */
    override suspend fun article(id: Int): Article? = dbQuery {
        Articles
            .select { Articles.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }


    /**
     * @param title: String  título del artículo.
     * @param body: String  cuerpo del artículo.
     *
     * La función addNewArticle es una función suspend que usa la función de orden superior
     * dbQuery para ejecutar una consulta de manera segura en la base de datos.
     *
     * En la consulta, se usa el método insert proporcionado por la biblioteca "Exposed" para
     * añadir un nuevo registro en la tabla "Articles". Se asignan los valores del título y del
     * cuerpo mediante la función lambda it y los parámetros title y body.
     *
     * @return Article?  retorna el nuevo registro como objeto Article con el método let de la
     * clase ResultRow, aplica el resultado que saque la consulta con el operador de elvis. Si
     * la consulta no retorna nada, devolvería null.
     */
    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }


    /**
     * @param id: Int identificador único del artículo.
     * @param title: String  título del artículo.
     * @param body: String  cuerpo del artículo.
     *
     * La función editArticle es una función suspend que usa la función de orden superior dbQuery
     * para ejecutar una consulta de forma más segura en la base de datos.
     *
     * Dentro de dbQuery, la función ejecuta una consulta para actualizar una fila específica
     * de la tabla "Articles" utilizando el método update proporcionado por la biblioteca
     * "Exposed". La función utiliza el operador eq para comparar el valor de la columna "id"
     * de la tabla "Articles" con el argumento id.
     *
     * La función luego especifica los nuevos valores para las columnas "title" y "body"
     * utilizando los operadores de asignación. La función utiliza el método > para comprobar
     * si la actualización afectó a alguna fila en la tabla.
     *
     * @return Boolean  retorna true si la actualización afectó a una fila o más, sino, retorna false.
     */
    override suspend fun editArticle(id: Int, title: String, body: String): Boolean = dbQuery {
        Articles.update({ Articles.id eq id }) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }


    /**
     * @param id: Int  identificador único del artículo.
     * La función deleteArticle es una función suspend que usa la función de orden superior dbQuery para
     * ejecutar una consulta de manera segura en la base de datos.
     *
     * La consulta elimina una fila específica de la tabla "Articles" mediante el método deleteWhere.
     * Además usa el operador eq para comparar el valor de la columna "id" de la tabla "Articles"
     * con el argumento id.
     *
     * @return Boolean  retorna true si por lo menos una fila es eliminada de la tabla "Articles" y
     * false en caso contrario.
     */
    override suspend fun deleteArticle(id: Int): Boolean = dbQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }





}


/*
    Se inicializa dao con una instancia de DAOFacadeImpl y se asegura de que haya al menos
    un artículo en la base de datos.
 */
val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allArticles().isEmpty()) {
            addNewArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}

