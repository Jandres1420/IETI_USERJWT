package edu.eci.usermicro.exception;


import edu.eci.usermicro.dto.ServerErrorResponseDto;
import edu.eci.usermicro.entities.enumerating.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {
    public InvalidCredentialsException() {
        super();
        new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}