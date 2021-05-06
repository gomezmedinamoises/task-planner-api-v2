package com.moisesgomez.taskplannerapiv2.services.tasks

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto
import com.moisesgomez.taskplannerapiv2.data.document.Task
import java.util.concurrent.atomic.AtomicLong

// Class without service annotation
class TaskServiceHashMap : TasksService {

    private val tasks = HashMap<String, Task>()
    private val nextOid = AtomicLong()

    override fun save(taskDto: TaskDto): Task {
        val task = Task(taskDto)
        task.id = nextOid.incrementAndGet().toString()
        tasks[taskDto.id] = task
        return task
    }

    override fun update(taskId: String, taskDto: TaskDto): Task {
        if (tasks.containsKey(taskId)) {
            val task = tasks[taskId]
            tasks[taskId] = Task(taskDto)
            tasks[taskId]!!.id = taskId
        }
        return tasks[taskId]!!
    }

    override fun findTaskById(taskId: String): Task? {
        return if (tasks.containsKey(taskId))
            tasks[taskId]
        else
            null
    }

    override fun findByResponsible(responsible: String): Task? {
        return tasks.values.find { responsible == it.responsible }
    }

    override fun all(): List<Task> {
        return tasks.values.toList()
    }

    override fun delete(taskId: String) {
        tasks.remove(taskId) != null
    }
}