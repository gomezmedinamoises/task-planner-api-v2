package com.moisesgomez.taskplannerapiv2.data.document

import com.moisesgomez.taskplannerapiv2.controller.dto.UserDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCrypt

@Document
data class User(
    @Id
    var id: String?,
    var fullName: String,
    var email: String,
    var passwordHash: String,
    val roles: List<RoleEnum>
) {
    fun update(userDto: UserDto) {
        fullName = userDto.fullName
        email = userDto.email
        passwordHash = BCrypt.hashpw(userDto.password, BCrypt.gensalt())
    }

    constructor(userDto: UserDto) : this(
        null,
        userDto.fullName,
        userDto.email,
        BCrypt.hashpw(userDto.password, BCrypt.gensalt()),
        listOf(RoleEnum.USER))
}