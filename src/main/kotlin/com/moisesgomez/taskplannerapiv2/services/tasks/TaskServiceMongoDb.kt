package com.moisesgomez.taskplannerapiv2.services.tasks

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto
import com.moisesgomez.taskplannerapiv2.data.document.Task
import com.moisesgomez.taskplannerapiv2.data.document.User
import com.moisesgomez.taskplannerapiv2.data.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskServiceMongoDb(
    @Autowired val taskRepository: TaskRepository
) : TasksService {
    override fun save(taskDto: TaskDto): Task {
        return taskRepository.save(Task(taskDto))
    }

    override fun update(taskId: String, taskDto: TaskDto): Task {
        val task = taskRepository.findById(taskId).get()
        task.update(taskDto)
        return taskRepository.save(task)
    }

    override fun findTaskById(taskId: String): Task? {
        return taskRepository.findById(taskId).orElse(null)
    }

    override fun findByResponsible(responsible: String): Task? {
        return taskRepository.findTaskByResponsible(responsible)
    }

    override fun all(): List<Task> {
        return taskRepository.findAll()
    }

    override fun delete(taskId: String) {
        taskRepository.deleteById(taskId)
    }
}