package com.moisesgomez.taskplannerapiv2.services.users

import com.moisesgomez.taskplannerapiv2.controller.dto.UserDto
import com.moisesgomez.taskplannerapiv2.data.document.User

interface UsersService {

    fun save(userDto: UserDto): User

    fun update(userId: String, userDto: UserDto): User

    fun findUserById(userId: String): User?

    fun findByEmail(email: String): User?

    fun all(): List<User>

    fun delete(userId: String)
}