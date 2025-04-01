package com.simpleepic.springbootcicd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpleepic.springbootcicd.dto.PersonDto;
import com.simpleepic.springbootcicd.dto.PostDto;
import com.simpleepic.springbootcicd.exception.ResourceNotFoundException;
import com.simpleepic.springbootcicd.service.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/persons")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        List<PersonDto> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK); // Return DTOs
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        PersonDto personDto = personService.getPersonById(id);
        return new ResponseEntity<>(personDto, HttpStatus.OK); // Return DTO
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto personDto) {
        PersonDto createdPersonDto = personService.createPerson(personDto);
        return new ResponseEntity<>(createdPersonDto, HttpStatus.CREATED); // Return DTO
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonDto updatedPersonDto) {
        PersonDto personDto = personService.updatePerson(id, updatedPersonDto);
        return new ResponseEntity<>(personDto, HttpStatus.OK); // Return DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{personId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByPersonId(@PathVariable Long personId) {
        List<PostDto> posts = personService.getPostsByPersonId(personId);
        return new ResponseEntity<>(posts, HttpStatus.OK); // Return DTOs
    }

    @PostMapping("/{personId}/posts")
    public ResponseEntity<PostDto> createPostForPerson(@PathVariable Long personId, @Valid @RequestBody PostDto postDto) {
        PostDto createdPostDto = personService.createPostForPerson(personId, postDto);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED); // Return DTO
    }
     @DeleteMapping("/{personId}/posts/{postId}")
    public ResponseEntity<Void> deletePostFromPerson(@PathVariable Long personId, @PathVariable Long postId) {
        personService.deletePostFromPerson(personId,postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Exception Handler (for ResourceNotFoundException) - Good practice!
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}