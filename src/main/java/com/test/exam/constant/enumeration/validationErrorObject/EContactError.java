package com.test.exam.constant.enumeration.validationErrorObject;

import com.test.exam.dto.ErrorExceptionDto;

/**
 * @author TranCung
 * @since 21/02/2023
 */
public enum EContactError implements ErrorObjectInterface{
    DUPLICATION_PHONE("C01", "Duplication phone number"),
    DUPLICATION_EMAIL("C02", "Duplication email"),
    CONTACT_NOT_FOUND("C03", "Contact Not Found");

    private String code;
    private String message;

    EContactError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public ErrorExceptionDto getErrorObject() {
        return new ErrorExceptionDto(this.code, this.message);
    }
}
