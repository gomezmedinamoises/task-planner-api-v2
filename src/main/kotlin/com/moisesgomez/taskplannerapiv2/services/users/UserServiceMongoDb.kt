package com.moisesgomez.taskplannerapiv2.services.users

import com.moisesgomez.taskplannerapiv2.controller.dto.UserDto
import com.moisesgomez.taskplannerapiv2.data.document.User
import com.moisesgomez.taskplannerapiv2.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceMongoDb(
    @Autowired val userRepository: UserRepository
) : UsersService {

    override fun save(userDto: UserDto): User {
        return userRepository.save(User(userDto))
    }

    override fun update(userId: String, userDto: UserDto): User {
        val user = userRepository.findById(userId).get()
        user.update(userDto)
        return userRepository.save(user)
    }

    override fun findUserById(userId: String): User? {
        return userRepository.findById(userId).orElse(null)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findUserByEmail(email)
    }

    override fun all(): List<User> {
        return userRepository.findAll()
    }

    override fun delete(userId: String) {
        userRepository.deleteById(userId)
    }
}