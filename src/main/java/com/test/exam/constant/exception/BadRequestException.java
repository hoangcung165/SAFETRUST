package com.test.exam.constant.exception;

import com.test.exam.dto.ErrorExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@Data
@AllArgsConstructor
public class BadRequestException extends Exception{
    private ErrorExceptionDto errorObject;
}
