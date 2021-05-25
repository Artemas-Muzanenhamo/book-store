package com.artemas.authorservice.integration

import com.artemas.authorservice.web.AuthorEndpoint
import com.artemas.authorservice.web.AuthorHandler
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebFluxTest
class AuthorEndpointIntegrationTest {

    @Test
    fun `Should add an Author`() {

    }
}