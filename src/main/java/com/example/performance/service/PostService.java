package com.example.performance.service;

import com.example.performance.domain.ex1.Post;
import com.example.performance.domain.ex1.PostDetails;
import com.example.performance.dto.PostDto;
import com.example.performance.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final List<Post> tenThousandPosts = randomListOf(10_000, Post.class, "comments");
    private final List<PostDetails> tenThousandPostDetails = randomListOf(10_000, PostDetails.class, "post");

    {
        int i = 0;

        for (PostDetails pd : tenThousandPostDetails) {
            pd.setPost(tenThousandPosts.get(i));
            i++;
        }
    }

    @Transactional
    public List<Post> getAllPostsWithAdHocEntityGraph() {
        return postRepository.getAllPostsWithAdHocEntityGraph();
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPostsWithAdHocEntityGraphReadOnly() {
        return postRepository.getAllPostsWithAdHocEntityGraph();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Post> getAllPostsWithAdHocEntityGraphReadOnlySupports() {
        return postRepository.getAllPostsWithAdHocEntityGraph();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<Long, Integer> countNumberOfCommentsPerPostBad() {
        return postRepository.findAll().stream().collect(toMap(Post::getId, v -> v.getComments().size()));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Object[]> countNumberOfCommentsPerPostGood() {
        return postRepository.countNumberOfCommentsPerPostGood();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<PostDto> getDtos() {
        return postRepository.getPostDtos();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<Post, PostDetails> searchInList1() {
        for (Post p : tenThousandPosts) {
            for (PostDetails pd : tenThousandPostDetails) {
                if (p.equals(pd.getPost())) {
                    doNothing();
                }
            }
        }

        return Map.of();
    }

    private void doNothing() {
        //skip
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<Post, PostDetails> searchInList2() {
        Map<Post, PostDetails> grouped = tenThousandPostDetails.stream().collect(toMap(PostDetails::getPost, Function.identity()));
        for (Post p : tenThousandPosts) {
            PostDetails postDetails = grouped.get(p);
            if (postDetails != null) {
                doNothing();
            }
        }
        return Map.of();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Post> removeDuplicates1() {
        List<Post> unique = new ArrayList<>();
        List<Post> posts = tenThousandPostDetails.stream().map(PostDetails::getPost).collect(toList());
        for (Post p : tenThousandPosts) {
            if (!posts.contains(p)) {
                unique.add(p);
            }
        }
        return unique;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Post> removeDuplicates2() {
        List<Post> unique = new ArrayList<>();
        Set<Post> posts = tenThousandPostDetails.stream().map(PostDetails::getPost).collect(toSet());
        for (Post p : tenThousandPosts) {
            if (!posts.contains(p)) {
                unique.add(p);
            }
        }
        return unique;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<Post> removeAllFromSet1() {
        Set<Post> posts = tenThousandPostDetails.stream().map(PostDetails::getPost).collect(Collectors.toSet());
        posts.removeAll(tenThousandPosts);
        return posts;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<Post> removeAllFromSet2() {
        Set<Post> posts = tenThousandPostDetails.stream().map(PostDetails::getPost).collect(Collectors.toSet());
        tenThousandPosts.forEach(posts::remove);
        return posts;
    }
}
