package com.tamastudy.restapi.mappers


interface BasicMapper<E, D> {
    fun toDto(postEntity: E): D
    fun toDtos(postEntities: List<E>): List<D>

    fun toEntity(postDto: D): E
    fun toEntities(postDtos: List<D>): List<E>
}