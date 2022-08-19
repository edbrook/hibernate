package uk.co.edbrook.hibernate.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.edbrook.hibernate.post.dto.PostCommentDto;
import uk.co.edbrook.hibernate.post.dto.PostDto;
import uk.co.edbrook.hibernate.post.projection.PostWithCommentsCount;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;

    public List<PostDto> allPostsWithComments() {
        return postRepo.findAllWithCommentsBy().stream()
            .map(PostService::postToDto)
            .collect(Collectors.toList());
    }

    public List<PostDto> allPosts() {
        return postRepo.findAll().stream()
            .map(PostService::postWithoutCommentsToDto)
            .collect(Collectors.toList());
    }

    public Optional<PostDto> getPostWithCommentsById(long id) {
        return postRepo.findWithCommentsById(id)
            .map(PostService::postToDto);
    }

    public Optional<PostDto> getPostById(long id) {
        return postRepo.findById(id)
            .map(PostService::postWithoutCommentsToDto);
    }

    public List<PostWithCommentsCount> allPostsWithCommentCount() {
        return postRepo.findAllWithCommentCount();
    }

    private static PostDto postWithoutCommentsToDto(Post p) {
        return PostDto.builder()
            .id(p.getId())
            .title(p.getTitle())
            .copy(p.getCopy())
            .build();
    }

    private static PostDto postToDto(Post p) {
        return PostDto.builder()
            .id(p.getId())
            .title(p.getTitle())
            .copy(p.getCopy())
            .comments(p.getComments().stream()
                .map(PostService::commentToDto)
                .collect(Collectors.toList()))
            .build();
    }

    private static PostCommentDto commentToDto(PostComment c) {
        return PostCommentDto.builder()
            .id(c.getId())
            .comment(c.getComment())
            .build();
    }
}
