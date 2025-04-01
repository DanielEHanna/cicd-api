// PostDto.java (in the same package as PersonDto)
package com.simpleepic.springbootcicd.dto;

import java.time.LocalDate;
import com.simpleepic.springbootcicd.model.Post;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private Long authorId; 

    // Constructors
    public PostDto() {}

    public PostDto(Long id, String title, String content, LocalDate createdAt, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.authorId = authorId;
    }
     // Getters and Setters
    public Long getId() {  return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) {this.content = content;}
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt;}
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    // Convert from Post entity to PostDto
    public static PostDto fromEntity(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getAuthor().getId());
    }
}