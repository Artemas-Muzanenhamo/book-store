package com.artemas.bookservice.service

import com.artemas.bookservice.domain.Book
import com.artemas.bookservice.repository.BookRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class BookServiceTest(
    @InjectMocks val bookService: BookService,
    @Mock val bookRepository: BookRepository
) {
    private val book = Book(789234L, 44333L, "Some Book Title Here")

    @Test
    fun `Should create a book`() {
        StepVerifier.create(bookService.addBook(book))
            .expectNext(book)
            .expectComplete()
            .verify()

        verify(bookRepository).save(book)
    }

    @Test
    fun `Should retrieve a book by id`() {
        StepVerifier.create(bookService.getBookById(book.id))
            .expectNext(book)
            .expectComplete()
            .verify()

        verify(bookRepository).findById(book.id)
    }
}