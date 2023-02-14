package com.osman.blog.payloads;

import com.osman.blog.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private int id;
    private String content;
}
