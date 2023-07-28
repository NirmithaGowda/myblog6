package com.blopapi.controller;


import com.blopapi.payload.PostDto;
import com.blopapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createpost(@Valid @RequestBody  PostDto postDto, BindingResult Result) {

        if(Result.hasErrors()){
            return  new ResponseEntity<>(Result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto saveDto = postService.createpost(postDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);//status 201
    }
    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto      =postService.getPostById(id);


        return new ResponseEntity<>(dto,HttpStatus.OK);//Status 200
    }
    //http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public List<PostDto> getAllPosts(

            @RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,      // handler method it reads from url and and intilze it


            @RequestParam(value = "pageSize", defaultValue = "5",required = false) int pageSize,

            @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir

    ){


        List<PostDto>  postDtos   = postService.getAllPost( pageNo, pageSize,sortBy,sortDir   );
        return  postDtos;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")

    public  ResponseEntity<String> deletePosts(@PathVariable("id") long id){
        postService.deletePosts(id);
        return new ResponseEntity<>("POST DELETED",HttpStatus.OK); //200
    }
    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id ,  @RequestBody PostDto postDto){
        PostDto dto     = postService.updatePost(id,postDto);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

}

