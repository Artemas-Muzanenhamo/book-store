package com.artemas.authorservice.integration

import com.artemas.authorservice.domain.Author
import com.artemas.authorservice.repository.AuthorRepository
import com.artemas.authorservice.service.AuthorService
import com.artemas.authorservice.web.AuthorEndpoint
import com.artemas.authorservice.web.AuthorHandler
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@WebFluxTest(AuthorEndpoint::class, AuthorHandler::class)
@Import(AuthorService::class)
@AutoConfigureDataMongo
class AuthorEndpointIntegrationTest {
    @Autowired
    lateinit var webTestClient: WebTestClient
    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Test
    fun `Should add an Author`() {
        val author = Author(123L, "artemas", "prime")

        val result = webTestClient.post()
            .uri("/authors/author")
            .body(just(author), Author::class.java)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isCreated
            .returnResult(Author::class.java)

        StepVerifier.create(result.responseBody)
            .expectNext(author)
            .expectComplete()
            .verify()
    }
}