package com.user.api.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class Encrypt {
        fun generateEncryptPassword(password : String) : String = BCryptPasswordEncoder().encode(password)
}