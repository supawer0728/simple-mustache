package com.parfait.study.simplemustache.post.model;

import com.samskivert.mustache.Mustache;
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

    public int doubledId() {
        return id * 2;
    }

    public Mustache.Lambda times() {
        return (frag, out) -> {
            Object context = frag.context();
            if (context instanceof Post) {
                Integer times = Integer.valueOf(frag.execute());
                Integer result = times * id;
                out.append(String.valueOf(result));
            }
        };
    }
}
