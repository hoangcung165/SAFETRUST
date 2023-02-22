package com.test.exam.validationService;

import com.test.exam.config.validation.contact.ContactValidation;
import com.test.exam.constant.enumeration.validationErrorObject.EContactError;
import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.requestDto.ContactRequestDto;
import com.test.exam.dto.responseDto.ContactResponseDto;
import com.test.exam.model.user.Contact;
import com.test.exam.repository.ContactRepository;
import com.test.exam.service.Implement.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author TranCung
 * @since 22/02/2023
 */
public class ContactServiceImplTest {
    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ContactValidation contactValidation;

    @InjectMocks
    private ContactServiceImpl contactService;

    private ContactRequestDto validContactRequestDto;

    /**
     * setup
     */
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        //set sample request Dto
        this.validContactRequestDto = new ContactRequestDto();
        this.validContactRequestDto.setName("David De Gea");
        this.validContactRequestDto.setEmail("deGeaManU@gmail.com");
        this.validContactRequestDto.setPhone("0987654321");
    }

    @Test
    public void testSaveOrUpdateContact_WithValidRequest_ShouldReturnSavedContact() throws BadRequestException{
        // Arrange
        Contact contact = new Contact();
        contact.setName("David De Gea");
        contact.setEmail("deGeaManU@gmail.com");
        contact.setPhone("0987654321");

        when(modelMapper.map(any(ContactRequestDto.class), any())).thenReturn(contact);
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        // Act
        ContactResponseDto savedContact = contactService.saveOrUpdateContact(validContactRequestDto);

        // Assert
        assertNotNull(savedContact);
        assertEquals("David De Gea", savedContact.getName());
        assertEquals("0987654321", savedContact.getPhone());
        assertEquals("deGeaManU@gmail.com", savedContact.getEmail());
    }


    @Test
    public void testSaveOrUpdateContact_WithInvalidRequest_ShouldThrowBadRequestException() throws BadRequestException {
        // Arrange
        Mockito.doThrow(new BadRequestException(EContactError.DUPLICATION_EMAIL.getErrorObject()))
                .when(contactValidation).validateRequestContact(any(ContactRequestDto.class));

        // Assert
        assertThrows(BadRequestException.class, () -> contactService.saveOrUpdateContact(new ContactRequestDto()));
    }
}
