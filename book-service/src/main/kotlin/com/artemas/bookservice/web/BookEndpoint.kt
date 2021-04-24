package com.artemas.bookservice.web

import com.artemas.bookservice.domain.Book
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class BookEndpoint {
    @PostMapping("/books/book")
    @ResponseStatus(CREATED)
    fun registerBook(@RequestBody book: Book): Mono<Book> {
        return Mono.just(book)
    }
}