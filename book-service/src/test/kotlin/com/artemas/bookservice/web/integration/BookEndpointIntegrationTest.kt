package com.artemas.bookservice.web.integration

import com.artemas.bookservice.domain.Book
import com.artemas.bookservice.repository.BookRepository
import com.artemas.bookservice.service.BookService
import com.artemas.bookservice.web.BookEndpoint
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono.just
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@WebFluxTest(BookEndpoint::class)
@Import(BookService::class)
internal class BookEndpointIntegrationTest {
    @Autowired
    lateinit var webTestClient: WebTestClient
    @MockBean
    lateinit var bookRepository: BookRepository

    private val book = Book(4132L, 987434L, "The Great Expectations of Prime")

    @Test
    fun `Should Add A Book`() {
        given(bookRepository.save(book)).willReturn(just(book)) // TODO: Use Real Test DB

        val result = webTestClient
            .post()
            .uri("/books/book")
            .body(just(book), Book::class.java)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isCreated
            .returnResult(Book::class.java)

        val responseBody: Flux<Book> = result.responseBody

        StepVerifier.create(responseBody)
            .expectNext(book)
            .expectComplete()
            .verify()
    }

    @Test
    fun `Should Retrieve A Book Given A Book Id`() {
        given(bookRepository.findById(book.id)).willReturn(just(book)) // TODO: Use Real Test DB

        val result = webTestClient
            .get()
            .uri("/books/${book.id}")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .returnResult(Book::class.java)

        val responseBody = result.responseBody

        StepVerifier.create(responseBody)
            .expectNext(book)
            .expectComplete()
            .verify()
    }
}