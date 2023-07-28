package com.blopapi.controller;

import com.blopapi.payload.CommentDto;
import com.blopapi.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentservice;

    public CommentController(CommentService commentservice) {
        this.commentservice = commentservice;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto comment = commentservice.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }
    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findcommentByPostId(@PathVariable("postId") long postId){
        List<CommentDto> dtos = commentservice.findCommebtByPostId(postId);
        return dtos;
    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<String> DeleteComment(@PathVariable("postId") long postId,@PathVariable(value="Id") long Id){
         commentservice.DeleteComment(postId,Id);
        return new ResponseEntity<>("comment is deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<CommentDto> getcommentById(@PathVariable("postId") long postId,@PathVariable(value="Id") long Id) {
        CommentDto dto = commentservice.getcommentById(postId, Id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @RequestBody CommentDto commentDto){

        CommentDto updatedComment = commentservice.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    }





