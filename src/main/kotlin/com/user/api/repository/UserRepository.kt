package com.user.api.repository

import com.user.api.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository <User, String> {

    fun findByEmail(email: String): MutableList<User>
    // fun removeById(id: String): Boolean
}
