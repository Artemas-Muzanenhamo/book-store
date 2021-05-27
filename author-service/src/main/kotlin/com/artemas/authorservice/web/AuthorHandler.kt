package com.artemas.authorservice.web

import com.artemas.authorservice.domain.Author
import com.artemas.authorservice.service.AuthorService
import com.artemas.authorservice.web.AuthorEndpoint.Companion.AUTHOR_URL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import reactor.core.publisher.Mono
import java.net.URI

@Component
class AuthorHandler(
    val authorService: AuthorService
) {
    fun createAuthor(request: ServerRequest): Mono<ServerResponse> {
        val newAuthorMono = request.bodyToMono(Author::class.java)
            .flatMap { author -> authorService.createNewAuthor(author) }
        return created(URI.create(AUTHOR_URL)).body(newAuthorMono, Author::class.java)
    }
}