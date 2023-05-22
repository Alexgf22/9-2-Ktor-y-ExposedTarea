package com.example.dao

import com.example.models.*

/**
 * La interfaz DAOFacade incluye los siguientes métodos que implementará la clase: DAOFacadeImpl
 * allArticles(), article(), addNewArticle(), editArticle() y deleteArticle()
 */
interface DAOFacade {
    suspend fun allArticles(): List<Article>
    suspend fun article(id: Int): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean
}