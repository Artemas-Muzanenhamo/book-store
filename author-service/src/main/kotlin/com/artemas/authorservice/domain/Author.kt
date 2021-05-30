package com.artemas.authorservice.domain

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "authors")
data class Author(
    @MongoId
    val id: Long,
    val authorName: String,
    val authorSurname: String
)
