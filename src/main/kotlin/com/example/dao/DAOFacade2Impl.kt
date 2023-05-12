package com.example.dao

import com.example.models.Article
import com.example.models.Articles
import com.example.models.Entities
import com.example.models.Entity
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class DAOFacade2Impl : DAOFacade2 {

    private fun resultRowToArticle(row: ResultRow) = Entity(
        id = row[Entities.id].toInt(),
        value = row[Entities.value].toInt(),
        name = row[Entities.name],
        description = row[Entities.description],
        sectionId = row[Entities.sectionId].toInt(),
        order = row[Entities.order]
    )
    override suspend fun allEntities(): List<Entity> = DatabaseFactory.dbQuery {
        Entities.selectAll().map(::resultRowToArticle)
    }

    override suspend fun entity(id: Int): Entity? = DatabaseFactory.dbQuery {
        TODO("Not yet implemented")
    }

    override suspend fun addNewEntity(title: String, body: String): Entity? = DatabaseFactory.dbQuery {
        TODO("Not yet implemented")
    }

    override suspend fun editEntity(id: Int, title: String, body: String): Boolean = DatabaseFactory.dbQuery {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEntity(id: Int): Boolean = DatabaseFactory.dbQuery {
        TODO("Not yet implemented")
    }
}