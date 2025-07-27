package com.example.users.data

import com.example.users.domain.User

internal fun JsonUser.toDomain(): User {
    val generatedId = id?.value ?: "${name.firstName}_${name.lastName}_${picture.medium}"
    return User(
        firstName = name.firstName,
        lastName = name.lastName,
        picture = picture.medium,
        id = generatedId,
    )
}