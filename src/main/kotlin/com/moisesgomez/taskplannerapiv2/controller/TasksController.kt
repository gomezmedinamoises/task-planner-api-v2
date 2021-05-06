package com.moisesgomez.taskplannerapiv2.controller

import com.moisesgomez.taskplannerapiv2.controller.dto.TaskDto
import com.moisesgomez.taskplannerapiv2.data.document.Task
import com.moisesgomez.taskplannerapiv2.exception.TaskNotFoundException
import com.moisesgomez.taskplannerapiv2.services.tasks.TasksService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/tasks")
class TasksController(@Autowired val tasksService: TasksService) {

    @PostMapping
    fun create(@RequestBody taskDto: TaskDto): Task {
        return tasksService.save(taskDto)
    }

    @GetMapping
    fun getTasks(): List<Task>? {
        return tasksService.all()
    }

    @GetMapping("/{id}")
    fun findTaskById(@PathVariable id: String): Task? {
        return tasksService.findTaskById(id) ?: throw TaskNotFoundException()
    }

    /*@GetMapping("/{responsible}")
    fun findTaskByResponsible(@PathVariable responsible: String): Task? {
        return tasksService.findByResponsible(responsible) ?: throw TaskNotFoundException()
    }*/

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody taskDto: TaskDto): Task? {
        return tasksService.update(id, taskDto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        return tasksService.delete(id)
    }
}