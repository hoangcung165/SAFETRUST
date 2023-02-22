package com.test.exam.service;

import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.requestDto.ContactFilterRequest;
import com.test.exam.dto.requestDto.ContactRequestDto;
import com.test.exam.dto.responseDto.CommonPageResponse;
import com.test.exam.dto.responseDto.ContactResponseDto;
import com.test.exam.model.user.Contact;

/**
 * @author TranCung
 * @since 20/02/2023
 */
public interface ContactService {
    /**
     * @author: TranCung
     * @since: 20/02/2023 23:59
     * @function: saveOrUpdateContact
     * @params: ContactRequestDto requestDto
     * @description:
     */
    ContactResponseDto saveOrUpdateContact(ContactRequestDto requestDto) throws BadRequestException;


    /**
     * @author: TranCung
     * @since: 21/02/2023 16:48
     * @function: getListContacts
     * @params:
     * @description:
     */
    CommonPageResponse<ContactResponseDto> getListContacts(ContactFilterRequest request);

    /**
     * @author: TranCung
     * @since: 21/02/2023 16:49
     * @function: findContactById
     * @params:
     * @description:
     */
    ContactResponseDto findContactById(Long id) throws BadRequestException;
}
