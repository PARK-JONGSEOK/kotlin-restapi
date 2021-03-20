package com.tamastudy.restapi.repositories.post

import com.tamastudy.restapi.entities.PostEntity

interface CustomPostRepository {
    fun getPosts_querydsl(): List<PostEntity>
}