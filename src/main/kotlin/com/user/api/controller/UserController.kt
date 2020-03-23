package com.user.api.controller

import com.user.api.domain.User
import com.user.api.entities.request.CreateUserRequest
import com.user.api.entities.request.DeleteRequest
import com.user.api.entities.request.UpdateUserRequest
import com.user.api.entities.response.DeleteResponse
import com.user.api.routers.UserRouter
import com.user.api.service.UserService
import com.user.api.utils.toFutureResponse
import java.util.concurrent.Future
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PutMapping(UserRouter.UPDATE_USER_V1)
    fun updateUser(
            @Valid @RequestBody updateUserRequest: UpdateUserRequest,
            @RequestHeader(value = "id") applicationUserId: String
    ): Future<User> {

        return userService.checkUpdateUser(updateUserRequest, applicationUserId).toFutureResponse()
    }

    @DeleteMapping(UserRouter.DELETE_USER_V1)
    fun removeUser(
        @Valid @RequestBody deleteRequest: DeleteRequest,
        @RequestHeader(value = "id") applicationUserId: String
    ): Future<DeleteResponse> {

        return userService.checkRemoveUser(deleteRequest, applicationUserId).toFutureResponse()
    }

    @PostMapping(UserRouter.CREATE_USER_V1)
    fun createUser(
            @Valid @RequestBody createUserRequest: CreateUserRequest
    ): Future<User> {

        return userService.checkCreateUser(createUserRequest).toFutureResponse()
    }
}
