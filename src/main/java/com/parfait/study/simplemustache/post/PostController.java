package com.parfait.study.simplemustache.post;

import com.parfait.study.simplemustache.post.model.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private static final ParameterizedTypeReference<List<Post>> postsListType = new ParameterizedTypeReference<List<Post>>() {
    };

    @GetMapping
    public String getList(Model model) {
        Mono<List<Post>> posts = WebClient.create("https://jsonplaceholder.typicode.com")
                                          .get()
                                          .uri("/posts")
                                          .retrieve()
                                          .bodyToMono(postsListType)
                                          .onErrorReturn(Collections.emptyList());

        model.addAttribute("post", posts);
        return "/posts/detail";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Mono<Post> post = WebClient.create("https://jsonplaceholder.typicode.com")
                                   .get()
                                   .uri("/posts/{id}", id)
                                   .retrieve()
                                   .bodyToMono(Post.class)
                                   .onErrorReturn(Post.EMPTY);

        model.addAttribute("post", post);
        return "/posts/detail";
    }
}