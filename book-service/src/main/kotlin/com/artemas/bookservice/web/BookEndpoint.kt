package com.artemas.bookservice.web

import com.artemas.bookservice.domain.Book
import com.artemas.bookservice.service.BookService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookEndpoint(
    val bookService: BookService
) {

    @PostMapping("/books/book", consumes = [APPLICATION_JSON_VALUE])
    @ResponseStatus(CREATED)
    fun registerBook(@RequestBody book: Book): Mono<Book> {
        return bookService.addBook(book)
    }

    @GetMapping("/books/{bookId}", produces = [APPLICATION_JSON_VALUE])
    fun getBookByBookId(@PathVariable bookId: Long): Mono<Book> {
        return bookService.getBookById(bookId)
    }

    @GetMapping("/books", produces = [APPLICATION_JSON_VALUE])
    fun getAllBooks(): Flux<Book> {
        return Flux.just(Book(id = 4132, authorId = 987434, title = "The Great Expectations of Prime"))
    }
}