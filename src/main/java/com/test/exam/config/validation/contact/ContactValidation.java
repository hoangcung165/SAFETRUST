package com.test.exam.config.validation.contact;

import com.test.exam.constant.enumeration.validationErrorObject.EContactError;
import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.requestDto.ContactRequestDto;
import com.test.exam.model.user.Contact;
import com.test.exam.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author TranCung
 * @since 21/02/2023
 */
@Component
public class ContactValidation {

    @Autowired
    private ContactRepository repository;
    /**
     * @author: TranCung
     * @since: 21/02/2023 11:28
     * @function: validateCreateContact
     * @params:
     * @description: validate exist info by request dto
     */
    public void validateRequestContact(ContactRequestDto dto) throws BadRequestException {

        // validate exist contact (update mode)
        if(Objects.nonNull(dto.getContactId())){
            Optional<Contact> optionalContact = repository.findById(dto.getContactId());
            if(!optionalContact.isPresent()){
                throw new BadRequestException(EContactError.CONTACT_NOT_FOUND.getErrorObject());
            }
        }
        //validate email
        if(Objects.nonNull(dto.getEmail())){
            Optional<Contact> optionalContact = repository.findByEmail(dto.getEmail());
            if(isDuplication(dto, optionalContact)){
                throw new BadRequestException(EContactError.DUPLICATION_EMAIL.getErrorObject());
            }
        }
        // validate phone
        if(Objects.nonNull(dto.getPhone())){
            Optional<Contact> optionalContact = repository.findByPhone(dto.getPhone());
            if(isDuplication(dto, optionalContact)){
                throw new BadRequestException(EContactError.DUPLICATION_PHONE.getErrorObject());
            }
        }
    }

    /**
     * @author: TranCung
     * @since: 21/02/2023 11:45
     * @function: isDuplication
     * @params:
     * @description: check new data exist in db
     */
    private boolean isDuplication(ContactRequestDto requestDto, Optional<Contact> dataExistOptional){
        return dataExistOptional.isPresent() && !dataExistOptional.get().getContactId().equals(requestDto.getContactId());
    }

    public void validateContactExist(Long id) throws BadRequestException{
        if(!this.repository.findById(id).isPresent()){
            throw new BadRequestException(EContactError.CONTACT_NOT_FOUND.getErrorObject());
        }
    }
}
