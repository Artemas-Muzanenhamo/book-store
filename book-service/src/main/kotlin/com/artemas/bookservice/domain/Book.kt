package com.artemas.bookservice.domain

data class Book(
    val id: Long,
    val authorId: Long,
    val title: String
)
