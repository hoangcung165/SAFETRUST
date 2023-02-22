package com.test.exam.constant.enumeration.validationErrorObject;

import com.test.exam.dto.ErrorExceptionDto;

/**
 * @author TranCung
 * @since 21/02/2023
 */
public enum ECommonException implements ErrorObjectInterface{
    INVALID_PARAM("CE001", "Invalid Param");

    private String code;
    private String message;

    ECommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public ErrorExceptionDto getErrorObject() {
        return new ErrorExceptionDto(this.code, this.message);
    }

    public ErrorExceptionDto getErrorObjectWithMessage(String message) {
        return new ErrorExceptionDto(this.code, message);
    }
}
