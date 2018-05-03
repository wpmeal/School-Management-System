package com.wpmeal.school.domain.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.wpmeal.school.domain.users.ContactInfo;

@RepositoryRestResource(path = "users/{id}/contactInfo")
public interface ContactInfoRepository extends CrudRepository<ContactInfo, Integer> {
	
	ContactInfo findByUserUId(int id);

}
