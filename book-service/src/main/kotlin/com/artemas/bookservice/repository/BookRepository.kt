package com.artemas.bookservice.repository

import com.artemas.bookservice.domain.Book
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: ReactiveMongoRepository<Book, Long>