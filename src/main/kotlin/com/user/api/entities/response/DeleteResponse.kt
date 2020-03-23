package com.user.api.entities.response

import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteResponse(

    @JsonProperty("id")
    var id: String = String(),
/*
    @JsonProperty("application_user_id")
    val applicationUserId: Long = 0,
*/
    @JsonProperty("msg")
    var message: String = ""
) {
    fun getDeleteUserResponse(id: String, message: String): DeleteResponse {

        return DeleteResponse(
            id = id, message = message
        )
    }
}
