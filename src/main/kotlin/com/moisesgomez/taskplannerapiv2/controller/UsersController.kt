package com.moisesgomez.taskplannerapiv2.controller

import com.moisesgomez.taskplannerapiv2.controller.dto.UserDto
import com.moisesgomez.taskplannerapiv2.data.document.ADMIN_ROLE
import com.moisesgomez.taskplannerapiv2.data.document.User
import com.moisesgomez.taskplannerapiv2.exception.UserNotFoundException
import com.moisesgomez.taskplannerapiv2.services.users.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/v1/users")
class UsersController(@Autowired val usersService: UsersService) {

    @PostMapping
    fun create(@RequestBody userDto: UserDto): User {
        return usersService.save(userDto)
    }

    @GetMapping
    fun getUsers(): List<User>? {
        return usersService.all()
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: String): User? {
        return usersService.findUserById(id) ?: throw UserNotFoundException()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody userDto: UserDto): User? {
        return usersService.update(id, userDto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        usersService.delete(id)
    }
}