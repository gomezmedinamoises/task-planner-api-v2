package com.moisesgomez.taskplannerapiv2.data.document

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto
import com.moisesgomez.taskplannerapiv2.controller.dto.UserDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCrypt

@Document
data class Task(
    @Id
    var id: String?,
    var description: String,
    var responsible: String,
    var status: String,
    val date: String
) {
    fun update(taskDto: TaskDto) {
        description = taskDto.description
        responsible = taskDto.responsible
        status = taskDto.status
    }

    constructor(taskDto: TaskDto) :
            this(null,
                taskDto.description,
                taskDto.responsible,
                taskDto.status,
                taskDto.date)
}