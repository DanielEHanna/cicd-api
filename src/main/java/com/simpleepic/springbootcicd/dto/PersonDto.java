package com.simpleepic.springbootcicd.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.simpleepic.springbootcicd.model.Person;
import com.simpleepic.springbootcicd.model.Post;
import com.simpleepic.springbootcicd.dto.PostDto; // Import PostDto


public class PersonDto {
    private Long id;
    private String name;
    private List<PostDto> posts; 

    // Constructors
    public PersonDto() {}

    public PersonDto(Long id, String name, List<PostDto> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    // Getters and setters 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id;}
    public String getName() { return name;}
    public void setName(String name) { this.name = name; }
    public List<PostDto> getPosts() { return posts; }
    public void setPosts(List<PostDto> posts) {this.posts = posts; }

    public static PersonDto fromEntity(Person person) {
        List<PostDto> postDtos = person.getPosts().stream()
                .map(post -> PostDto.fromEntity(post))  // Use PostDto.fromEntity
                .collect(Collectors.toList());
        return new PersonDto(person.getId(), person.getName(), postDtos);
    }

        @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}