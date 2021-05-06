package com.moisesgomez.taskplannerapiv2.data.document

const val ADMIN_ROLE = "ADMIN"
const val USER_ROLE = "USER"
const val GUEST_ROLE = "GUEST"

enum class RoleEnum(
    val role: String
) {
    ADMIN(ADMIN_ROLE),
    USER(USER_ROLE),
    GUEST(GUEST_ROLE)
}