package com.simpleepic.springbootcicd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simpleepic.springbootcicd.dto.PersonDto;
import com.simpleepic.springbootcicd.dto.PostDto;
import com.simpleepic.springbootcicd.exception.ResourceNotFoundException;
import com.simpleepic.springbootcicd.model.Person;
import com.simpleepic.springbootcicd.model.Post;
import com.simpleepic.springbootcicd.repository.PersonRepository;
import com.simpleepic.springbootcicd.repository.PostRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PostRepository postRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, PostRepository postRepository) {
        this.personRepository = personRepository;
        this.postRepository = postRepository;
    }

    // --- Person Methods ---
    @Transactional(readOnly = true)
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonDto::fromEntity) 
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonDto getPersonById(Long id) {
        return personRepository.findById(id)
                .map(PersonDto::fromEntity) 
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
    }

    @Transactional
    public PersonDto createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());

        Person savedPerson = personRepository.save(person);
        return PersonDto.fromEntity(savedPerson);
    }

    @Transactional
    public PersonDto updatePerson(Long id, PersonDto updatedPersonDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));

        person.setName(updatedPersonDto.getName());
        // Update other fields as needed, but *don't* directly manipulate the posts here.

        Person updatedPerson = personRepository.save(person);
        return PersonDto.fromEntity(updatedPerson);
    }
    @Transactional
    public void deletePerson(Long id) {
         Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
        personRepository.delete(person); // Delete using the entity object
    }

    // --- Post Methods (using PostDto) ---

    @Transactional(readOnly = true)
    public List<PostDto> getPostsByPersonId(Long personId) {
        return postRepository.findByAuthor_Id(personId).stream()
                .map(PostDto::fromEntity) //
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDto createPostForPerson(Long personId, PostDto postDto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + personId));

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAuthor(person); 
        person.addPost(post);   

        //Save post
        Post savedPost = postRepository.save(post);

        return PostDto.fromEntity(savedPost);
    }

    @Transactional
    public void deletePostFromPerson(Long personId, Long postId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + personId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        if (!post.getAuthor().equals(person)) {
            throw new IllegalArgumentException("Post does not belong to this person"); 
        }

        person.removePost(post); 
        personRepository.save(person); 
    }
}