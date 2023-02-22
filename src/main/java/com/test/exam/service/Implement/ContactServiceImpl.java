package com.test.exam.service.Implement;

import com.test.exam.config.validation.contact.ContactValidation;
import com.test.exam.constant.enumeration.validationErrorObject.EContactError;
import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.requestDto.ContactFilterRequest;
import com.test.exam.dto.requestDto.ContactRequestDto;
import com.test.exam.dto.responseDto.CommonPageResponse;
import com.test.exam.dto.responseDto.ContactResponseDto;
import com.test.exam.model.user.Contact;
import com.test.exam.repository.ContactRepository;
import com.test.exam.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContactValidation contactValidation;


    @Override
    public ContactResponseDto saveOrUpdateContact(ContactRequestDto requestDto) throws BadRequestException {

        // validate
        contactValidation.validateRequestContact(requestDto);

        // by pass validate
        Contact contact = this.modelMapper.map(requestDto, Contact.class); // convert to model entity

        return this.modelMapper.map(this.contactRepository.save(contact), ContactResponseDto.class);
    }

    /**
     * @author: TranCung
     * @since: 21/02/2023 16:48
     * @function: getListContacts
     * @params:
     * @description:
     */
    @Override
    public CommonPageResponse<ContactResponseDto> getListContacts(ContactFilterRequest request) {
        Page<Contact> contactPage;
        if(Objects.nonNull(request.getName()) && !request.getName().isBlank()){
            contactPage = this.contactRepository.findAllByNameLike(request.buildPageRequest(), "%" + request.getName() + "%");
        }else {
            contactPage = this.contactRepository.findAll(request.buildPageRequest());
        }


        return CommonPageResponse
                .<ContactResponseDto>builder()
                .totalItems(contactPage.getTotalElements())
                .totalPage(contactPage.getTotalPages())
                .pageSize(contactPage.getNumberOfElements())
                .page(contactPage.getNumber() + 1)
                .data(contactPage.getContent().stream().map(
                        contact -> this.modelMapper.map(contact, ContactResponseDto.class)
                ).collect(Collectors.toList()))
                .build();
    }

    /**
     * @author: TranCung
     * @since: 21/02/2023 16:48
     * @function: findContactById
     * @params:
     * @description:
     */
    @Override
    public ContactResponseDto findContactById(Long id) throws BadRequestException {
        Optional<Contact> contact = this.contactRepository.findById(id);

        // validate
        // not use validate class because it consuming 1 request to db
        if(!contact.isPresent()){
            throw new BadRequestException(EContactError.CONTACT_NOT_FOUND.getErrorObject());
        }
        return this.modelMapper.map(contact.get(), ContactResponseDto.class);
    }
}
