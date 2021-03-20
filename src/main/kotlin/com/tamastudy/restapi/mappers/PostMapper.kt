package com.tamastudy.restapi.mappers

import com.tamastudy.restapi.dto.post.CreatePostDto
import com.tamastudy.restapi.dto.post.PostDto
import com.tamastudy.restapi.dto.post.UpdatePostDto
import com.tamastudy.restapi.entities.PostEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PostMapper {
    companion object {
        val INSTANCE = Mappers.getMapper(PostMapper::class.java)
    }

    fun toDto(postEntity: PostEntity): PostDto
    fun toDtos(postEntities: List<PostEntity>): List<PostDto>

    fun toEntity(postDto: PostDto): PostEntity
    fun toEntities(postDtos: List<PostDto>): List<PostEntity>

    fun toEntityCreatePost(createPostDto: CreatePostDto): PostEntity
    fun toEntityUpdatePost(updatePostDto: UpdatePostDto): PostEntity

}
