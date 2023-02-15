package com.practice.khstudy.service;

import com.practice.khstudy.domain.Post;
import com.practice.khstudy.repository.PostRepository;
import com.practice.khstudy.request.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;

    public void write(PostRequest postRequest) {
        // repository.save()
        // postCreate -> Entity
//        requestDTO
        //responseDTO Data Transfer Object 데이터 요청, 응답에 사용하는 객체
        Post post = new Post(postRequest.getTitle(), postRequest.getContent());
        postRepository.save(post);
    }
}
