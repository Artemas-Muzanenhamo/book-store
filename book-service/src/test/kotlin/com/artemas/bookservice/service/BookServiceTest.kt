package com.artemas.bookservice.service

import com.artemas.bookservice.domain.Book
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class BookServiceTest {
    private val bookService = BookService()

    @Test
    fun `Should create a book`() {
        val book = Book(789234L, 44333L, "Some Book Title Here")

        StepVerifier.create(bookService.addBook(book))
            .expectNext(book)
            .expectComplete()
            .verify()
    }
}