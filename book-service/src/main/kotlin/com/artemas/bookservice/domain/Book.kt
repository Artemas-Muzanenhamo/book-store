package com.artemas.bookservice.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "books")
data class Book(
    @MongoId
    val id: Long,
    val authorId: Long,
    val title: String
)
