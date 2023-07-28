package com.blopapi.service;

import com.blopapi.entity.Comment;
import com.blopapi.entity.Post;
import com.blopapi.exceptions.ResourceNotFoundExpection;
import com.blopapi.payload.CommentDto;
import com.blopapi.repository.CommentRepository;
import com.blopapi.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private PostRepository postRepo;

    private CommentRepository commentRepo;

    private ModelMapper modelMapper;




    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
  Post post     = postRepo. findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExpection(postId)
        );

        Comment comment= new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post);

        Comment savecomment = commentRepo.save(comment);

        CommentDto dto = new CommentDto ();
        dto.setId(savecomment.getId());
        dto.setName(savecomment.getName());
        dto.setEmail(savecomment.getEmail());
        dto.setBody(savecomment.getBody());

        return dto;
    }

    public List<CommentDto> findCommebtByPostId(long postId){
        Post post     = postRepo. findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExpection(postId)
        );
        List<Comment> comments = commentRepo.findByPostId(postId);

      return   comments.stream().map(comment -> maptoDto(comment)).collect(Collectors.toList());

    }

    @Override
    public void DeleteComment(long postId, long id) {
        Post post     = postRepo. findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExpection(postId)
        );
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundExpection(id)
        );
        commentRepo.deleteById(id);
    }

    @Override
    public CommentDto getcommentById(long postId, long id) {
        Post post     = postRepo. findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExpection(postId)
        );
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundExpection(id)
        );
    CommentDto  commentDto   =  maptoDto(comment);
     return commentDto;

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExpection( postId));

        // retrieve comment by id
        Comment comment = commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundExpection( commentId));


        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepo.save(comment);
        return maptoDto(updatedComment);

    }




    CommentDto maptoDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return  dto;
    }
    Comment comment(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return  comment;
    }
}
