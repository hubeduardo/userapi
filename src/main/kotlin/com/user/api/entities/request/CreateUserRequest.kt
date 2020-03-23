package com.user.api.entities.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateUserRequest(

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("name")
    var name: String = "",

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("last_name")
    var lastName: String = "",

    @field:NotNull
    @field:NotEmpty
    @field:Email
    @JsonProperty("email")
    var email: String = "",

    @field:NotNull
    @field:NotEmpty
    @JsonProperty("cpf")
    @get:CPF()
    val CPF: String = "",

    @JsonProperty("image_url")
    var imageUrl: String = "",

    @field:NotNull
    @field:NotEmpty
    @field:Suppress
    @JsonProperty("password")
    var password: String = "",


    @JsonProperty("birthday")
    var birthday: LocalDate?
)
