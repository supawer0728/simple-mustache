package com.parfait.study.simplemustache.post;

import com.parfait.study.simplemustache.post.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Mono<Post> post = executeGetPost(id);
        model.addAttribute("post", post);
        return "/posts/detail";
    }

    private Mono<Post> executeGetPost(Long id) {

        return WebClient.create("https://jsonplaceholder.typicode.com")
                        .get()
                        .uri("/posts/{id}", id)
                        .retrieve()
                        .bodyToMono(Post.class)
                        .onErrorReturn(Post.EMPTY);
    }
}
