package uk.co.edbrook.hibernate.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.co.edbrook.hibernate.post.projection.PostWithCommentsCount;

import java.util.List;
import java.util.Optional;

interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph("post_with_comments")
    List<Post> findAllWithCommentsBy();

    @EntityGraph("post_with_comments")
    Optional<Post> findWithCommentsById(Long aLong);

    @Query("SELECT p.id as id, p.title as title, p.copy as copy, COUNT(pc.id) as commentCount " +
        "FROM Post p LEFT JOIN PostComment pc ON pc.post = p " +
        "GROUP BY p " +
        "ORDER BY commentCount DESC")
    List<PostWithCommentsCount> findAllWithCommentCount();
}
