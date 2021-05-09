package com.artemas.bookservice.service

import com.artemas.bookservice.domain.Book
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BookService {
    fun addBook(book: Book): Mono<Book> {
        return Mono.just(book)
    }

    fun getBookById(bookId: Long) {
        TODO("Not yet implemented")
    }
}