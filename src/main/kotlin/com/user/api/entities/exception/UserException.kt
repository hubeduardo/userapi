package com.user.api.entities.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
open class UserException(code: String, message: String, status: HttpStatus = HttpStatus.BAD_REQUEST) : RuntimeException() {

    var code = code
    override var message = message
    var status = status
}
