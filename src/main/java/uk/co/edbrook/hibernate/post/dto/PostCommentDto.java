package uk.co.edbrook.hibernate.post.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostCommentDto {

    long id;

    String comment;
}
