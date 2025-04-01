package com.simpleepic.springbootcicd.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "posts") // Keep your original 'posts' table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id") // Good practice to be explicit
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 1000) // Example length
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false) // Foreign key column to persons table
    private Person author; // Use 'author' to represent the relationship

    // Constructors
    public Post() {
        this.createdAt = LocalDate.now();
    }

    public Post(String title, String content, Person author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDate.now();
    }


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Person getAuthor() { return author; }  // Use author
    public void setAuthor(Person author) { this.author = author; } // Use author
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt;}

    // equals, hashCode, and toString (for completeness)

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}'; // No author here!
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}