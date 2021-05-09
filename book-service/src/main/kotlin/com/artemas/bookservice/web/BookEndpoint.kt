package com.artemas.bookservice.web

import com.artemas.bookservice.domain.Book
import com.artemas.bookservice.service.BookService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/books/{bookId}")
    fun getBookByBookId(@PathVariable bookId: Long): Mono<Book> {
//        return bookService.getBookById(bookId)
        return Mono.just(Book(4132L, 987434L, "The Great Expectations of Prime"))
    }
}