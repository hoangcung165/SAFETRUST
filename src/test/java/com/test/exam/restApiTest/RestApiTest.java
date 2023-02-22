package com.test.exam.restApiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.requestDto.ContactRequestDto;
import com.test.exam.dto.responseDto.ContactResponseDto;
import com.test.exam.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author TranCung
 * @since 22/02/2023
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * get detail
     * @throws Exception
     */
    @Test
    void getContactById_ShouldReturnContactResponseDto() throws Exception {
        //sample
        ContactResponseDto responseDto = new ContactResponseDto();
        Long contactId = 1L;
        responseDto.setContactId(contactId);
        responseDto.setName("David De Gea");
        responseDto.setEmail("davidDeGea@gmail.com");
        responseDto.setPhone("0987654321");

        when(contactService.findContactById(contactId)).thenReturn(responseDto);

        //
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contact/{contactId}", contactId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId").value(contactId))
                .andExpect(jsonPath("$.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.phone").value(responseDto.getPhone()))
                .andExpect(jsonPath("$.email").value(responseDto.getEmail()));
    }

    @Test
    public void testCreateContact_ShouldReturnContactResponse_SaveSuccess() throws Exception{
        // Object Info
        Long contactId = 1L;
        String name = "David De Gea";
        String email = "davidDeGea@gmail.com";
        String phone = "0987654321";
        // Request
        ContactRequestDto requestDto = new ContactRequestDto();
        requestDto.setEmail(email);
        requestDto.setName(name);
        requestDto.setPhone(phone);

        // Response
        ContactResponseDto responseDto = new ContactResponseDto();
        responseDto.setContactId(contactId);
        responseDto.setName(name);
        responseDto.setEmail(email);
        responseDto.setPhone(phone);

        //behaviour
        when(contactService.saveOrUpdateContact(any(ContactRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId").value(contactId))
                .andExpect(jsonPath("$.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.phone").value(responseDto.getPhone()))
                .andExpect(jsonPath("$.email").value(responseDto.getEmail()));

    }
}
