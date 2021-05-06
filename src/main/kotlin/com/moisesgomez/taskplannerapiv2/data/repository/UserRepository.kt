package com.moisesgomez.taskplannerapiv2.data.repository

import com.moisesgomez.taskplannerapiv2.data.document.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findUserByEmail(email: String): User?
}