package com.user.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.user.api.entities.request.CreateUserRequest
import com.user.api.entities.request.UpdateUserRequest
import com.user.api.security.Encrypt
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.access.annotation.Secured
import java.time.LocalDate


@Document(collection = "user")
data class User(

    @Id
    @JsonProperty("id")
    val id: String? = ObjectId().toHexString(),

 /*
    @JsonProperty("application_user_id")
    val applicationUserId: Long = 0,
 */
    @JsonProperty("name")
    val name: String = "",

    @JsonProperty("last_name")
    val lastName: String = "",

    @JsonProperty("email")
    val email: String = "",

    @JsonProperty("cpf")
    val CPF: String = "",

    @JsonProperty("image_url")
    val imageUrl: String = "",

    @JsonProperty("version")
    val version: Long = 1,

    @JsonProperty("password")
    val password: String = "",

   @JsonProperty("birthday")
   val birthday: LocalDate? = null

) {
    fun mergeDataUser(newUser: UpdateUserRequest, oldUser: User): User {

        return User(
            id = oldUser.id,
            name = if (newUser.name == "") oldUser.name else newUser.name,
            lastName = if (newUser.lastName == "") oldUser.lastName else newUser.lastName,
            email = if (newUser.email == "") oldUser.email else newUser.email,
            imageUrl = if (newUser.imageUrl == "") oldUser.imageUrl else newUser.imageUrl,
            version = oldUser.version + 1,
            birthday = newUser.birthday ?: oldUser.birthday

        )
    }

    fun convertToUser(createUserRequest: CreateUserRequest): User {

        return User(
            name = createUserRequest.name,
            lastName = createUserRequest.lastName,
            email = createUserRequest.email,
            CPF = createUserRequest.CPF,
            imageUrl = createUserRequest.imageUrl,
            password = Encrypt().generateEncryptPassword(createUserRequest.password),
            birthday = createUserRequest.birthday
        )
    }
}
