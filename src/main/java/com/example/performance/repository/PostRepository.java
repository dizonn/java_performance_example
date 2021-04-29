package com.example.performance.repository;

import com.example.performance.domain.ex1.Post;
import com.example.performance.dto.PostDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.comments")
    List<Post> getAllPostsWithWrongJoinFetch();

    @Query("select distinct p from Post p left join fetch p.comments")
    List<Post> getAllPostsWithWrightJoinFetchAndRemovedDuplicates();

    @Query("select p from Post p")
    @EntityGraph(attributePaths = {"comments"})
    List<Post> getAllPostsWithAdHocEntityGraph();

    @Query("select p.id, count(c.id) from Post p left join p.comments c group by p.id")
    List<Object[]> countNumberOfCommentsPerPostGood();

    @Query("select p.id as id, p.title as title, c as comments from Post p left join p.comments c")
    List<PostDto> getPostDtos();
}
