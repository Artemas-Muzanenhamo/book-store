package com.artemas.authorservice.service

import com.artemas.authorservice.domain.Author
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthorService {
    fun createNewAuthor(author: Author): Mono<Author> {
        return Mono.just(author)
    }
}