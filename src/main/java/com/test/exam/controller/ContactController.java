package com.test.exam.controller;

import com.test.exam.constant.SystemConstant;
import com.test.exam.constant.exception.BadRequestException;
import com.test.exam.dto.requestDto.ContactFilterRequest;
import com.test.exam.dto.requestDto.ContactRequestDto;
import com.test.exam.dto.responseDto.CommonPageResponse;
import com.test.exam.dto.responseDto.ContactResponseDto;
import com.test.exam.service.ContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@RestController
@RequestMapping({SystemConstant.API_PREFIX + "/contact"})
@ApiOperation("Contract Controller")
public class ContactController {

    @Autowired
    private ContactService contactService;

    /**
     * @author: TranCung
     * @since: 21/02/2023 16:14
     * @function: createContact
     * @params: 
     * @description:
     */
    @ApiOperation(value = "Create a contact", notes = "Return Contact created")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "created"),
            @ApiResponse(code = 400, message = "Bad Request - a exception wiht param request or duplication database")
    })
    @PostMapping
    public ContactResponseDto createContact(@RequestBody @Valid ContactRequestDto dto) throws BadRequestException {
        return this.contactService.saveOrUpdateContact(dto);
    }

    /**
     * @author: TranCung
     * @since: 22/02/2023 10:30
     * @function: getListContact
     * @params:
     * @description:
     */
    @ApiOperation(value = "Get List Contact with paging request And filter by name", notes = "Return Pagging information and data by request")
    @GetMapping
    public CommonPageResponse<ContactResponseDto> getListContact(@RequestBody ContactFilterRequest request){
        return this.contactService.getListContacts(request);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Detail Information Contact")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return Information Of contact By contactId"),
            @ApiResponse(code = 400, message = "Bad Request - Contact Not found")
    })
    public ContactResponseDto findById(@PathVariable("id") Long id) throws BadRequestException{
        return this.contactService.findContactById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update information of contact")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Success"),
            @ApiResponse(code = 400, message = "A error with request or not found by id")
    })
    public ContactResponseDto updateContactById(@PathVariable("id") Long id, @RequestBody @Valid ContactRequestDto requestDto) throws BadRequestException{
        // set id into dto
        requestDto.setContactId(id);
        return this.contactService.saveOrUpdateContact(requestDto);
    }

}
