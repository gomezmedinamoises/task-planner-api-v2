package com.moisesgomez.taskplannerapiv2.controller.auth

import com.moisesgomez.taskplannerapiv2.data.document.User
import com.moisesgomez.taskplannerapiv2.data.dto.LoginDto
import com.moisesgomez.taskplannerapiv2.data.dto.TokenDto
import com.moisesgomez.taskplannerapiv2.services.users.UsersService
import com.moisesgomez.taskplannerapiv2.utils.CLAIMS_ROLES_KEY
import com.moisesgomez.taskplannerapiv2.utils.TOKEN_DURATION_MINUTES
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("v1/auth")
class AuthController(
    @Value("\${app.secret}") val secret: String,
    @Autowired val usersService: UsersService
) {

    @PostMapping
    fun authenticate(@RequestBody loginDto: LoginDto): TokenDto {
        val expirationDate = Calendar.getInstance()
        expirationDate.add(Calendar.MINUTE, TOKEN_DURATION_MINUTES)
        val token = generateAppToken("1213213", expirationDate.time)
        return TokenDto(token, expirationDate.time)
    }

    private fun generateToken(user: User, expirationDate: Date): String {
        return Jwts.builder()
            .setSubject(user.id)
            .claim(CLAIMS_ROLES_KEY, user.roles)
            .setIssuedAt(Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    private fun generateAppToken(userId: String, expirationDate: Date): String {
        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun generateTokenDto(user: User): TokenDto {
        val expirationDate = Calendar.getInstance()
        expirationDate.add(Calendar.MINUTE, TOKEN_DURATION_MINUTES)
        val token = generateToken(user, expirationDate.time)
        return TokenDto(token, expirationDate.time)
    }
}