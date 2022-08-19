package uk.co.edbrook.hibernate.post;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NamedEntityGraph(
    name = "post_with_comments",
    attributeNodes = {
        @NamedAttributeNode("comments")
    }
)
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String copy;

    @OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PostComment> comments;
}
