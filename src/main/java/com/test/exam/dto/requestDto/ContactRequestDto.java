package com.test.exam.dto.requestDto;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@Data
public class ContactRequestDto implements Serializable {


    private Long contactId;

    @NotBlank
    private String name;


    @Pattern(regexp = "^0\\d{9}", message = "Invalid Phone Number")
    private String phone;

    @Email(message = "Invalid email")
    private String email;
}
