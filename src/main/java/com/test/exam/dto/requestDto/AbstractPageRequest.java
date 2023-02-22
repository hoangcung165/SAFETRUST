package com.test.exam.dto.requestDto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author TranCung
 * @since 21/02/2023
 */
@Data
public class AbstractPageRequest implements Serializable {

    private int page = 1;

    private int maxItems = 20;

    public Pageable buildPageRequest(){
        if(Objects.isNull(page) || page < 1){
            this.page = 1;
        }
        if(Objects.isNull(maxItems) || maxItems < 1){
            this.maxItems = 20;
        }
        return PageRequest.of(this.page - 1, this.maxItems);
    }
}
