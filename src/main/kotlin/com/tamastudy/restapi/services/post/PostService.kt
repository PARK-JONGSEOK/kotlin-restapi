package com.tamastudy.restapi.services.post

import com.tamastudy.restapi.dto.post.CreatePostDto
import com.tamastudy.restapi.dto.post.PostDto
import com.tamastudy.restapi.dto.post.UpdatePostDto
import java.util.*

interface PostService {
    fun getPosts(): List<PostDto>
    fun getPost(postId: Long): PostDto
    fun createPost(createPostDto: CreatePostDto)
    fun updatePost(postId: Long, updatePostDto: UpdatePostDto)
    fun deletePost(postId: Long)
}