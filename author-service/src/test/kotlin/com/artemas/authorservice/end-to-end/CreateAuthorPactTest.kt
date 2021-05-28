package com.artemas.authorservice.`end-to-end`

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import org.apache.http.client.fluent.Request.Post
import org.apache.http.entity.ContentType.APPLICATION_JSON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import java.io.IOException


@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "book-service")
class CreateAuthorPactTest {

    @Pact(provider = "book-service", consumer = "author-service")
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {
        return builder
            .given("request to create a book given an author id")
            .uponReceiving("CreateAuthorPactTest test interaction")
            .path("/books/book")
            .method("POST")
            .body("{\"responsetest\": true}")
            .headers("Content-Type", APPLICATION_JSON_VALUE)
            .willRespondWith()
            .status(200)
            .toPact()
    }

    @Test
    @Throws(IOException::class)
    fun test(mockServer: MockServer) {
        val httpResponse = Post(mockServer.getUrl() + "/books/book").bodyString("{\"responsetest\": true}", APPLICATION_JSON).execute().returnResponse()
        assertThat(httpResponse.statusLine.statusCode).isEqualTo(200)
    }
}