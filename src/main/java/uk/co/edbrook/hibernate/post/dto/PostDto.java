package uk.co.edbrook.hibernate.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@JsonInclude(NON_NULL)
public class PostDto {

    long id;

    String title;

    String copy;

    List<PostCommentDto> comments;
}
