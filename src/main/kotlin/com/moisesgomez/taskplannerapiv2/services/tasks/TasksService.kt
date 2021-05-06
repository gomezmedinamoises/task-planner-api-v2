package com.moisesgomez.taskplannerapiv2.services.tasks

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto
import com.moisesgomez.taskplannerapiv2.data.document.Task

interface TasksService {

    fun save(taskDto: TaskDto): Task

    fun update(taskId: String, taskDto: TaskDto): Task

    fun findTaskById(taskId: String): Task?

    fun findByResponsible(responsible: String): Task?

    fun all(): List<Task>

    fun delete(taskId: String)
}