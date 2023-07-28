package com.blopapi.service;


import com.blopapi.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createpost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    void deletePosts(long id);


    PostDto updatePost(long id, PostDto postDto);
}
