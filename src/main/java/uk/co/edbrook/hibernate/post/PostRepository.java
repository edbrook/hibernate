package uk.co.edbrook.hibernate.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph("post_with_comments")
    List<Post> findAllWithCommentsBy();

    @EntityGraph("post_with_comments")
    Optional<Post> findWithCommentsById(Long aLong);
}
