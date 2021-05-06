package com.moisesgomez.taskplannerapiv2.data.document

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Task(
    @Id
    val id: String?,
    val description: String,
    val responsible: String,
    val status: String,
    val date: String
) {
    constructor(taskDto: TaskDto) :
            this(null,
                taskDto.description,
                taskDto.responsible,
                taskDto.status,
                taskDto.date)
}