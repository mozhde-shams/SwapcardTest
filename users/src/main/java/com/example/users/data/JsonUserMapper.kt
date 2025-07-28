package com.example.users.data

import com.example.users.domain.User

internal fun JsonUser.toDomain(): User {
    return User(
        firstName = name.firstName,
        lastName = name.lastName,
        picture = picture.medium,
        id = id.value,
    )
}