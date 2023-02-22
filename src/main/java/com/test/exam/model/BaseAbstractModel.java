package com.test.exam.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * @author TranCung
 * @since 20/02/2023
 * @description: Basic info for many model use
 */
@MappedSuperclass
@Data
public class BaseAbstractModel {

    /**
     * flag logic delete or disable record
     * @default : false
     * @NotNull
     */
    @Column(name = "disable", nullable = false, columnDefinition = "boolean default false")
    private Boolean disable = false;

    /**
     * time record create
     * @NotAllowUpdate
     */
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    /**
     * last modified record
     */
    @LastModifiedDate
    @Column(name = "last_modified")
    private Timestamp lastModifiedDate;
}
