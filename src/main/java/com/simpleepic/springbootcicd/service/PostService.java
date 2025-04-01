package com.simpleepic.springbootcicd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleepic.springbootcicd.model.Post;
import com.simpleepic.springbootcicd.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public  Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post post, Long id){
        Post postFound = postRepository.findById(id).get();

       if(postFound != null){
           postFound.setContent(post.getContent());
  
          return postRepository.save(postFound);
      }

        return null;
    }

    public void deletePost(Long id) {
        Post postFound = postRepository.findById(id).get();

        if(postFound != null) {
            postRepository.deleteById(id);
        }
    }
}
