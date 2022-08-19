package uk.co.edbrook.hibernate.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import uk.co.edbrook.hibernate.post.PostService;
import uk.co.edbrook.hibernate.post.dto.PostDto;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Mono<PostsResponse> getAllPosts(
        @RequestParam(name = "comments", required = false, defaultValue = "false") boolean comments) {

        return Mono.fromCallable(comments
                ? postService::allPostsWithComments
                : postService::allPosts)
            .map(PostsResponse::new)
            .publishOn(Schedulers.boundedElastic());
    }

    @GetMapping("/{id}")
    public Mono<PostDto> getPostWithCommentsById(
        @PathVariable long id,
        @RequestParam(name = "comments", required = false, defaultValue = "false") boolean comments) {

        return Mono.fromCallable(() ->
                (comments
                    ? postService.getPostWithCommentsById(id)
                    : postService.getPostById(id)
                ).orElse(null))
            .publishOn(Schedulers.boundedElastic());
    }
}
