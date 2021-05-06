package com.moisesgomez.taskplannerapiv2.data.repository

import com.moisesgomez.taskplannerapiv2.data.document.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, String> {

    fun findTaskByResponsible(responsible: String): Task?

}