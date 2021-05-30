package com.artemas.authorservice.service

import com.artemas.authorservice.domain.Author
import com.artemas.authorservice.repository.AuthorRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthorService(
    val authorRepository: AuthorRepository
) {
    fun createNewAuthor(author: Author): Mono<Author> {
        return authorRepository.save(author)
    }
}