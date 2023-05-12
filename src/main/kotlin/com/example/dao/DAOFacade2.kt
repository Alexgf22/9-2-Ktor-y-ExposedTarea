package com.example.dao

import com.example.models.*

interface DAOFacade2 {

    suspend fun allEntities(): List<Entity>
    suspend fun entity(id: Int): Entity?
    suspend fun addNewEntity(title: String, body: String): Entity?
    suspend fun editEntity(id: Int, title: String, body: String): Boolean
    suspend fun deleteEntity(id: Int): Boolean

}