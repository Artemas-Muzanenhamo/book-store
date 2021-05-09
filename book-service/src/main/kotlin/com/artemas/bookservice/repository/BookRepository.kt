package com.artemas.bookservice.repository

import com.artemas.bookservice.domain.Book
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: MongoRepository<Book, Long>