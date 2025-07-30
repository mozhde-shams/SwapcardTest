package com.example.users.data

import com.example.users.domain.User
import java.security.MessageDigest

fun sha256KeyOf(model: Any): String =
    when (model) {
        is JsonUser -> sha256HashOf(
            model.name.firstName,
            model.name.lastName,
            model.picture.medium,
            model.id.value
        )

        is User -> sha256HashOf(
            model.firstName,
            model.lastName,
            model.picture,
            model.id
        )

        else -> error("Unsupported type for sha256KeyOf: ${model::class}")
    }

private fun sha256HashOf(vararg segments: String?): String {
    val input = segments.map { it.orEmpty() }
        .joinToString("|")
    val digest = MessageDigest
        .getInstance("SHA-256")
        .digest(input.toByteArray(Charsets.UTF_8))
    return digest.joinToString("") { "%02x".format(it) }
}