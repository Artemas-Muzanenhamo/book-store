package com.artemas.authorservice.service

import com.artemas.authorservice.domain.Author
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.test.StepVerifier

class AuthorServiceTest {
    private val authorService = AuthorService()

    @Test
    fun `Should create a new author`() {
        val author = Author(9875L, "Morgan", "Tsvangirai")

        StepVerifier.create(authorService.createNewAuthor(author))
            .expectNext(author)
            .expectComplete()
            .verify()
    }
}