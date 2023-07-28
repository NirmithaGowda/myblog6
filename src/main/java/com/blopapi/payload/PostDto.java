package com.blopapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {


    private long id;

    @NotEmpty
    @Size(min=2 ,message = "title should be atleast 2 words")
    private String title;

    @NotEmpty(message = "description is empty")
    @Size(min=4 ,message = "description should be atleast 4 words")
    private String description ;

    @NotEmpty(message = "content is empty")
    private String content;

}