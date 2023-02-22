package com.test.exam.dto.requestDto;

import lombok.Data;

/**
 * @author TranCung
 * @since 21/02/2023
 */
@Data
public class ContactFilterRequest extends AbstractPageRequest{

    private String name;
}
