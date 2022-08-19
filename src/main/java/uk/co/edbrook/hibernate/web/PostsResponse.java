package uk.co.edbrook.hibernate.web;

import lombok.Builder;
import lombok.Value;
import uk.co.edbrook.hibernate.post.dto.PostDto;

import java.util.List;

@Value
@Builder
public class PostsResponse {
    List<PostDto> posts;
}
