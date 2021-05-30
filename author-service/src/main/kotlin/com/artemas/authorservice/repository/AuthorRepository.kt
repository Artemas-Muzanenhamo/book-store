package com.artemas.authorservice.repository

import com.artemas.authorservice.domain.Author
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: ReactiveMongoRepository<Author, Long>