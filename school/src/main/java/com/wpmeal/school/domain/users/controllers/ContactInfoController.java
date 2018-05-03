package com.wpmeal.school.domain.users.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wpmeal.school.domain.users.ContactInfo;
import com.wpmeal.school.domain.users.User;
import com.wpmeal.school.domain.users.repositories.ContactInfoRepository;
import com.wpmeal.school.domain.users.repositories.UserRepository;

@RepositoryRestController
@RequestMapping("/users/{id}/contactInfo")
@EnableEntityLinks
@ExposesResourceFor(ContactInfo.class)

public class ContactInfoController {

	private ContactInfoRepository repository;
	private UserRepository userRepository;

	@Autowired
	EntityLinks entityLinks;

	@Autowired
	public ContactInfoController(ContactInfoRepository repo, UserRepository userrepo) {
		repository = repo;
		userRepository = userrepo;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Resource<ContactInfo>> getContactInfo(@PathVariable int id) {
		ContactInfo contactInfo = repository.findOne(id);
		Resource<ContactInfo> resource = resourceProcess(contactInfo);
		return new ResponseEntity<Resource<ContactInfo>>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Resource<ContactInfo>> saveContactInfo(@PathVariable int id,
			@RequestBody ContactInfo contactInfo) {
		User user = userRepository.findOne(id);
		contactInfo.setUser(user);
		if (!repository.exists(id))
			repository.save(contactInfo);
		contactInfo = repository.findOne(id);
		Resource<ContactInfo> resource = resourceProcess(contactInfo);
		return new ResponseEntity<Resource<ContactInfo>>(resource, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Resource<ContactInfo>> updateContactInfo(@PathVariable int id,
			@RequestBody ContactInfo contactInfo) {
		contactInfo.setContactId(id);
		repository.save(contactInfo);
		contactInfo = repository.findOne(id);
		Resource<ContactInfo> resource = resourceProcess(contactInfo);
		return new ResponseEntity<Resource<ContactInfo>>(resource, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> delContactInfo(@PathVariable int id) {
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public Resource<ContactInfo> resourceProcess(ContactInfo contactInfo) {
		Resource<ContactInfo> resource = new Resource<ContactInfo>(contactInfo);
		resource.add(ControllerLinkBuilder
				.linkTo(methodOn(ContactInfoController.class).getContactInfo(contactInfo.getContactId()))
				.withSelfRel());
		return resource;
	}
}
