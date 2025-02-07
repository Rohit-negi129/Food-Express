package com.project.UserAuthentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Customer already exists")
// Use the@ResponseStatus annotation to set the exception message and status
public class UserAlreadyExistsException extends  Exception{
}
