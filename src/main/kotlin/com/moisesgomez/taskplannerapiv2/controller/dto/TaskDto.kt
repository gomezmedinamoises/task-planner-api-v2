package com.moisesgomez.taskplannerapiv2.controller.dto

data class TaskDto(
    val id: String,
    val description: String,
    val responsible: String,
    val status: String,
    val date: String
)