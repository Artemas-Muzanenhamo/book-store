package com.artemas.authorservice.`end-to-end`

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import au.com.dius.pact.core.model.annotations.PactDirectory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.http.client.fluent.Request.Post
import org.apache.http.entity.ContentType.APPLICATION_JSON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import java.io.IOException


@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "book-service")
@PactDirectory("../pacts")
class CreateAuthorPactTest {

    val json: PactDslJsonBody = PactDslJsonBody()
        .numberType("id", 123456789L)
        .numberType("authorId", 987654321L)
        .stringType("title", "The Greatest Pact Alive")

    @Pact(provider = "book-service", consumer = "author-service")
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {
        return builder
            .given("request to create a book given an author id")
            .uponReceiving("CreateAuthorPactTest test interaction")
            .path("/books/book")
            .method("POST")
            .body(json)
            .headers("Content-Type", APPLICATION_JSON_VALUE)
            .willRespondWith()
            .status(CREATED.value())
            .body(json)
            .headers(mapOf(Pair("Content-Type", APPLICATION_JSON_VALUE)))
            .toPact()
    }

    @Test
    @Throws(IOException::class)
    fun test(mockServer: MockServer) {
        val httpResponse = Post(mockServer.getUrl() + "/books/book")
            .bodyString(json.toString(), APPLICATION_JSON)
            .execute()
            .returnResponse()

        assertThat(httpResponse.statusLine.statusCode)
            .isNotNull
            .isEqualTo(CREATED.value())

        val objectMapper = jacksonObjectMapper()
        val responseBody = objectMapper.readValue(httpResponse.entity.content, Book::class.java)
        assertThat(responseBody)
            .isNotNull
            .isEqualTo(Book(123456789, 987654321, "The Greatest Pact Alive"))
    }
}

data class Book(
    val id: Long,
    val authorId: Long,
    val title: String
)