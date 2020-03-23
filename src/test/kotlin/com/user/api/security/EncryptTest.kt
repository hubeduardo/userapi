package com.user.api.security

import org.junit.Assert
import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class EncryptTest {

    private val password = "1qw2!@#de"
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun testGeneratePassword(){
        val hash = Encrypt().generateEncryptPassword(password);
        Assert.assertTrue(bCryptEncoder.matches(password, hash))
    }

}