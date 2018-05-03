package com.wpmeal.school.domain.users.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.wpmeal.school.domain.users.Admin;

@RepositoryRestResource
public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {

}
