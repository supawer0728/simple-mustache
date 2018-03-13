package com.parfait.study.simplemustache;

import com.parfait.study.simplemustache.post.model.Post;
import com.samskivert.mustache.Mustache;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LayoutAdvice {

    @ModelAttribute("layout")
    public Mustache.Lambda layout() {
        return (frag, out) -> {
            Object context = frag.context();
            if (context instanceof Post) {
                Integer times = Integer.valueOf(frag.execute());
                Integer result = times * ((Post) context).getId();
                out.append(String.valueOf(result));
            }
        };
    }
}
