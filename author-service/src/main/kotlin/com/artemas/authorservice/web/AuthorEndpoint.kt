package com.artemas.authorservice.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class AuthorEndpoint {

    @Bean
    fun authorRoutes(authorHandler: AuthorHandler): RouterFunction<ServerResponse> {
        return route(
            POST(AUTHOR_URL),
            authorHandler::createAuthor
        )
    }

    companion object {
        const val AUTHOR_URL = "/authors/author"
    }
}