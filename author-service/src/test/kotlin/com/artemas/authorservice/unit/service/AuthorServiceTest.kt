package com.artemas.authorservice.unit.service

import com.artemas.authorservice.domain.Author
import com.artemas.authorservice.repository.AuthorRepository
import com.artemas.authorservice.service.AuthorService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono.just
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class AuthorServiceTest(
    @Mock val authorRepository: AuthorRepository
) {
    private val authorService = AuthorService(authorRepository)

    @Test
    fun `Should create a new author`() {
        val author = Author(9875L, "Morgan", "Tsvangirai")
        given(authorRepository.save(author)).willReturn(just(author))

        StepVerifier.create(authorService.createNewAuthor(author))
            .expectNext(author)
            .expectComplete()
            .verify()

        verify(authorRepository).save(author)
    }
}