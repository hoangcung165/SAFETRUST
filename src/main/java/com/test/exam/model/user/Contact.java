package com.test.exam.model.user;

import com.test.exam.constant.enumeration.EContactStatus;
import com.test.exam.model.BaseAbstractModel;
import lombok.Data;

import javax.persistence.*;

/**
 * @author TranCung
 * @since 20/02/2023
 * @description: User model
 */
@Data
@Entity
@Table(name = "contact")
public class Contact extends BaseAbstractModel {

    /**
     * primary key user id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    /**
     * name
     * @Length: 50
     */
    @Column(name = "name")
    private String name;


    /**
     * email
     * @Unique
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * phone
     * @Length: 50
     * @Unique
     */
    @Column(name = "phone", length = 15, unique = true)
    private String phone;




}
