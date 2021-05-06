package com.moisesgomez.taskplannerapiv2.controller.dto

data class UserDto(
    val id: String,
    val fullName: String,
    val email: String,
    val password: String
)