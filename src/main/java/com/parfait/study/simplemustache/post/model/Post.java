package com.parfait.study.simplemustache.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    public static final Post EMPTY = new Post(0, "");

    private int id;
    private String body;
}
