package com.test.exam.dto.responseDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author TranCung
 * @since 21/02/2023
 */
@Data
public class ContactResponseDto implements Serializable {
    private Long contactId;

    private String name;

    private String phone;

    private String email;
}
