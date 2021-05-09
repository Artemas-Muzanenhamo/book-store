package com.artemas.bookservice.service

import com.artemas.bookservice.domain.Book
import com.artemas.bookservice.repository.BookRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun addBook(book: Book): Mono<Book> {
        return bookRepository.save(book)
    }

    fun getBookById(bookId: Long): Mono<Book> {
        return bookRepository.findById(bookId)
    }
}