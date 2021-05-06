package com.moisesgomez.taskplannerapiv2.data.dto

import java.util.*

data class TokenDto(
    val token: String,
    val expirationTime: Date
)