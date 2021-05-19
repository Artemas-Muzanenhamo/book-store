package com.artemas.bookservice.service

import com.artemas.bookservice.domain.Book
import com.artemas.bookservice.repository.BookRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono.just
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class BookServiceTest(
    @Mock val bookRepository: BookRepository
) {
    private val bookService = BookService(bookRepository)
    private val book = Book(789234L, 44333L, "Some Book Title Here")

    @Test
    fun `Should create a book`() {
        given(bookRepository.save(book)).willReturn(just(book))

        StepVerifier.create(bookService.addBook(book))
            .expectNext(book)
            .expectComplete()
            .verify()

        verify(bookRepository).save(book)
    }

    @Test
    fun `Should retrieve a book by id`() {
        given(bookRepository.findById(book.id)).willReturn(just(book))

        StepVerifier.create(bookService.getBookById(book.id))
            .expectNext(book)
            .expectComplete()
            .verify()

        verify(bookRepository).findById(book.id)
    }
}