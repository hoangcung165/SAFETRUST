package com.test.exam.repository;

import com.test.exam.model.user.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author TranCung
 * @since 20/02/2023
 */
@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {


    Optional<Contact> findByPhone(String phone);

    Optional<Contact> findByEmail(String email);

    Page<Contact> findAllByNameLike(Pageable pageable, String name);


}
