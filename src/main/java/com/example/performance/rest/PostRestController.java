package com.example.performance.rest;

import com.example.performance.domain.ex1.Post;
import com.example.performance.domain.ex1.PostDetails;
import com.example.performance.dto.PostDto;
import com.example.performance.repository.PostRepository;
import com.example.performance.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostRestController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/lazyInit1")
    public List<Post> getPostsLazyInit1() {
        return postRepository.findAll();
    }

    @GetMapping("/lazyInit2")
    public List<Post> getPostsLazyInit2() {
        return postRepository.getAllPostsWithWrongJoinFetch();
    }

    @GetMapping("/lazyInit3")
    public List<Post> getPostsLazyInit4() {
        return postRepository.getAllPostsWithWrightJoinFetchAndRemovedDuplicates();
    }

    @GetMapping("/lazyInit4")
    public List<Post> getPostsLazyInit5() {
        return postRepository.getAllPostsWithAdHocEntityGraph();
    }

    @GetMapping("/transaction1")
    public List<Post> getPostsInTransaction() {
        return postService.getAllPostsWithAdHocEntityGraph();
    }

    @GetMapping("/transaction2")
    public List<Post> getPostsInTransactionReadOnly() {
        return postService.getAllPostsWithAdHocEntityGraphReadOnly();
    }

    @GetMapping("/transaction3")
    public List<Post> getPostsInTransactionReadOnlySupports() {
        return postService.getAllPostsWithAdHocEntityGraphReadOnlySupports();
    }

    @GetMapping("/logicInCode1")
    public Map<Long, Integer> countNumberOfCommentsPerPostBad() {
        return postService.countNumberOfCommentsPerPostBad();
    }

    @GetMapping("/logicInCode2")
    public List<Object[]> countNumberOfCommentsPerPostGood() {
        return postService.countNumberOfCommentsPerPostGood();
    }

    @GetMapping("/dto")
    public List<PostDto> getDtos() {
        return postService.getDtos();
    }

    @GetMapping("/searchInList1")
    public Map<Post, PostDetails> searchInList1() {
        return postService.searchInList1();
    }

    @GetMapping("/searchInList2")
    public Map<Post, PostDetails> searchInList2() {
        return postService.searchInList2();
    }

    @GetMapping("/removeDuplicates1")
    public List<Post> removeDuplicates1() {
        return postService.removeDuplicates1();
    }

    @GetMapping("/removeDuplicates2")
    public List<Post> removeDuplicates2() {
        return postService.removeDuplicates2();
    }

    @GetMapping("/removeAllFromSet1")
    public Set<Post> removeAllFromSet1() {
        return postService.removeAllFromSet1();
    }

    @GetMapping("/removeAllFromSet2")
    public Set<Post> removeAllFromSet2() {
        return postService.removeAllFromSet2();
    }
}

