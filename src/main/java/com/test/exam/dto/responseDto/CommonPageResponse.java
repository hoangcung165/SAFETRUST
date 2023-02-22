package com.test.exam.dto.responseDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author TranCung
 * @since 21/02/2023
 */
@Data
@Builder
public class CommonPageResponse<T> {
    private Integer page;
    private Long totalItems;
    private Integer totalPage;
    private Integer pageSize;
    private List<T> data;
}
