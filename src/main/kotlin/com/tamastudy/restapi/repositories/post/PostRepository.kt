package com.tamastudy.restapi.repositories.post

import com.tamastudy.restapi.entities.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository:JpaRepository<PostEntity, Long> {
}