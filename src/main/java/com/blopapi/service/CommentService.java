package com.blopapi.service;

import com.blopapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    public List<CommentDto> findCommebtByPostId(long postId);

    void DeleteComment(long postId, long id);

    CommentDto getcommentById(long postId, long id);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);


}
