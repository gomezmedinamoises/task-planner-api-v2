package com.moisesgomez.taskplannerapiv2.data.document

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto

data class Task(
    val oid: Long,
    val id: String,
    val description: String,
    val responsible: String,
    val status: String,
    val date: String
) {
    constructor(oid: Long, taskDto: TaskDto) :
            this(oid,
                taskDto.id,
                taskDto.description,
                taskDto.responsible,
                taskDto.status,
                taskDto.date)
}