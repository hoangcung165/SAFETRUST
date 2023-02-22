package com.test.exam.validationTest;

import com.test.exam.dto.requestDto.ContactRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author TranCung
 * @since 21/02/2023
 */
@ExtendWith(SpringExtension.class)
public class ValidationRequestTest {

    private Validator validator;

    @BeforeEach
    public void setup(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * @author: TranCung
     * @since: 21/02/2023 23:34
     * @function: givenContactRequestDto_whenNameIsNull_thenValidationFails
     * @params: 
     * @description:
     */
    @Test
    public void givenContactRequestDto_whenNameIsNull_thenValidationFails(){
        ContactRequestDto requestDto = new ContactRequestDto();
        requestDto.setName(null);
        Set<ConstraintViolation<ContactRequestDto>> violations = validator.validate(requestDto);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("must not be blank", violations.iterator().next().getMessage());
    }


    /**
     * @author: TranCung
     * @since: 22/02/2023 00:02
     * @function: givenContactRequestDto_whenEmailInvalid_thenValidationFails
     * @params: 
     * @description:
     */
    @Test
    public void givenContactRequestDto_whenEmailInvalid_thenValidationFails(){
        ContactRequestDto requestDto = new ContactRequestDto();
        requestDto.setName("tranCung");
        requestDto.setEmail("InvalidEmail");
        Set<ConstraintViolation<ContactRequestDto>> violations = validator.validate(requestDto);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("Invalid email", violations.iterator().next().getMessage());
    }

    /**
     * @author: TranCung
     * @since: 22/02/2023 00:02
     * @function: givenContactRequestDto_whenPhoneNumberIsInvalid_thenValidationFails
     * @params: 
     * @description:
     */
    @Test
    public void givenContactRequestDto_whenPhoneNumberIsInvalid_thenValidationFails(){
        ContactRequestDto requestDto = new ContactRequestDto();
        requestDto.setName("tranCung");
        requestDto.setEmail("emailTesting@gmail.com");
        requestDto.setPhone("123456789");
        Set<ConstraintViolation<ContactRequestDto>> violations = validator.validate(requestDto);
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("Invalid Phone Number", violations.iterator().next().getMessage());
    }


}
