package uk.co.edbrook.hibernate.post.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface PostWithCommentsCount {

    Long getId();

    String getTitle();

    String getCopy();

    @JsonProperty("comment_count")
    Integer getCommentCount();
}
