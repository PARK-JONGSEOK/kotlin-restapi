package com.tamastudy.restapi.services.post

import com.tamastudy.restapi.dto.post.CreatePostDto
import com.tamastudy.restapi.dto.post.PostDto
import com.tamastudy.restapi.dto.post.UpdatePostDto
import com.tamastudy.restapi.entities.PostEntity
import com.tamastudy.restapi.mappers.PostMapper
import com.tamastudy.restapi.repositories.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    companion object {
        val LOG: Logger = Logger.getLogger(PostServiceImpl::class.java.name)
    }

    override fun getPosts(): List<PostDto> {
        return PostMapper.INSTANCE.toDtos(postRepository.getPosts_querydsl())
    }

    override fun getPost(postId: Long): PostDto {
        return postRepository.findByIdOrNull(postId)?.let { postEntity ->
            PostMapper.INSTANCE.toDto(postEntity)
        } ?: kotlin.run {
            LOG.warning("$postId 에 해당하는 포스트를 찾을 수 없습니다.")
            throw RuntimeException()
        }
    }

    override fun createPost(createPostDto: CreatePostDto) {
        // 변환 작업
        val newPost = PostMapper.INSTANCE.toEntityCreatePost(createPostDto)
            .also {
                LOG.info("newPost: $it")
            }
        postRepository.save(newPost)
    }

    override fun updatePost(postId: Long, updatePostDto: UpdatePostDto) {
        val foundPost = postRepository.findByIdOrNull(postId) ?: kotlin.run {
            LOG.warning("$postId 에 해당하는 포스트를 찾을 수 없습니다.")
            throw RuntimeException()
        }
        // 변환 작업
        val newPost = foundPost.apply {
            this.title = updatePostDto.title
            this.content = updatePostDto.content
        }.also {
            LOG.info("updatedPost: $it")
        }
        postRepository.save(newPost)
    }

    override fun deletePost(postId: Long) {
        postRepository.findByIdOrNull(postId)?.let { foundPost ->
            postRepository.deleteById(foundPost.id!!)
        } ?: kotlin.run {
            LOG.warning("$postId 에 해당하는 포스트를 찾을 수 없습니다.")
            throw RuntimeException()
        }
    }
}