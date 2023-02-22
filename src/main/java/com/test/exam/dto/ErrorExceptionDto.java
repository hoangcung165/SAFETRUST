package com.test.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorExceptionDto implements Serializable {
    /**
     * custom code default
     */
    private String code;

    /**
     * message about problem
     */
    private String message;
}
