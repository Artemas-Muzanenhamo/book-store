package com.artemas.bookservice.repository

import com.artemas.bookservice.domain.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class BookRepositoryTest(
    @Autowired val bookRepository: BookRepository
    ) {

    @Test
    fun `Should save new book`() {
        val book = Book(43298784L, 9874L, "Newly Added Book By Arty")
        bookRepository.save(book)
    }
}