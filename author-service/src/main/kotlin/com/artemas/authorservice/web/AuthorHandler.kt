package com.artemas.authorservice.web

import com.artemas.authorservice.domain.Author
import com.artemas.authorservice.web.AuthorEndpoint.Companion.AUTHOR_URL
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class AuthorHandler {
    fun createAuthor(request: ServerRequest): Mono<ServerResponse> {
        val newAuthorMono = request.bodyToMono(Author::class.java)
        return ServerResponse.created(URI.create(AUTHOR_URL)).body(newAuthorMono, Author::class.java)
    }
}