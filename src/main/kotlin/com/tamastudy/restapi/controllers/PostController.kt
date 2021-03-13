package com.tamastudy.restapi.controllers

import com.tamastudy.restapi.dto.post.CreatePostDto
import com.tamastudy.restapi.dto.post.PostDto
import com.tamastudy.restapi.dto.post.UpdatePostDto
import com.tamastudy.restapi.services.post.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PostController(
    private val postService: PostService
) {
    @GetMapping("/v1/post")
    fun getPosts(): ResponseEntity<List<PostDto>> {
        val posts = postService.getPosts()
        return ResponseEntity.status(HttpStatus.OK).body(posts)
    }

    @GetMapping("/v1/post/{postId}")
    fun getPost(
        @PathVariable postId: Long
    ): ResponseEntity<PostDto> {
        val post = postService.getPost(postId)
        return ResponseEntity.status(HttpStatus.OK).body(post)
    }

    @PostMapping("/v1/post")
    fun createPost(
        @Valid @RequestBody createPostDto: CreatePostDto
    ): ResponseEntity<Nothing> {
        postService.createPost(createPostDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(null)
    }

    @PatchMapping("/v1/post/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @Valid @RequestBody updatePostDto: UpdatePostDto
    ): ResponseEntity<Nothing> {
        postService.updatePost(postId, updatePostDto)
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    @DeleteMapping("/v1/post/{postId}")
    fun deletePost(
        @PathVariable postId: Long
    ): ResponseEntity<Nothing> {
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }
}