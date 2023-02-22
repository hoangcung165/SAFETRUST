package com.test.exam.config.handleException;

import com.test.exam.constant.enumeration.validationErrorObject.ECommonException;
import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.ErrorExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@ControllerAdvice("com.test.exam.controller")
public class RestApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestApiExceptionHandler.class);

    /**
     * handle bad request
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExceptionDto> badRequestException(BadRequestException ex){
        //validate Null and set default value
        ErrorExceptionDto errorExceptionDto =  Objects.nonNull(ex.getErrorObject()) ? ex.getErrorObject() : new ErrorExceptionDto("ER01", "An unknown error");

        //log
        log.error("Bad Request : {}", errorExceptionDto.getMessage(), errorExceptionDto.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        errorExceptionDto
        );
    }



    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExceptionDto> handleParamRequest(MethodArgumentNotValidException e){
        String message = "Some properties has passed incorrectly.";
        if (e.getBindingResult().getFieldError() != null) {
            message = e.getBindingResult().getFieldError().getField() + ": " + e.getBindingResult().getFieldError().getDefaultMessage();
        }
        log.error("Validation failed with message: {}", message, e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ECommonException.INVALID_PARAM.getErrorObjectWithMessage(message)
                );
    }
}
