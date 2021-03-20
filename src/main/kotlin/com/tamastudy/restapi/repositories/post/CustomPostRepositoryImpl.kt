package com.tamastudy.restapi.repositories.post

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.tamastudy.restapi.entities.PostEntity
import com.tamastudy.restapi.entities.QPostEntity
import com.tamastudy.restapi.entities.QPostEntity.*
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

class CustomPostRepositoryImpl(
    private var queryfactory: JPAQueryFactory
): CustomPostRepository {

    override fun getPosts_querydsl(): List<PostEntity> {
        return queryfactory.selectFrom(postEntity).fetch()
    }
}