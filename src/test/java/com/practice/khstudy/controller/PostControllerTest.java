package com.practice.khstudy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.khstudy.domain.Post;
import com.practice.khstudy.repository.PostRepository;
import com.practice.khstudy.request.PostRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/get 요청시 Hello World를 출력한다.")
    void indexTest() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"))
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    @DisplayName("param 테스트")
//    void get테스트() throws Exception {
//        //expected
//        mockMvc.perform(MockMvcRequestBuilders.get("/paramtest")
//                        .param("title","kh")
//                        .param("content","study"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("kh : study"))
//                .andDo(MockMvcResultHandlers.print());
//
//    }

//    @Test
//    @DisplayName("APPLICATION_FORM_URLENCODED")
//    void APPLICATION_FORM_URLENCODED_TEST() throws Exception {
//        //expected
//        mockMvc.perform(post("/posts")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("title", "제목 테스트")
//                        .param("content", "내용 테스트")
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello"))
//                .andDo(MockMvcResultHandlers.print());
//
//    }

    @Test
    @DisplayName("2번 /posts 요청시 Hello World를 출력한다.")
    void postTest() throws Exception {  // 가능하면 application/json을 권장합니다. (기존 application/x-www-form-urlencoded)
        //  글 제목
        // 글 내용
        // 사용자
        // id
        // name
        // level
        /** json 형식으로 보낼 경우 user 키의 value에 대해 key value를 가진 object를 넣는 등 계층 구조의 데이터를 보낼 때 효과적입니다. 반면 기존의 key=value를 &로 이어서 보내는 application/x-www-form-urlencoded 형식은 데이터를 표현하는 데에 한계가 있습니다.
         * {
         *  "title": "xx",
         *  "content": "xx",
         *  "user": {
         *          "id":"xx",
         *          "name": "xx",
         *          "lever": "xx"
         *      }
         * }
         */

        PostRequest request = new PostRequest("제목입니다.", "내용입니다.");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        System.out.println(json);

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\": \"ㅇㅇㅇㅇ\", \"content\": \"컨텐츠\"}"))
                        .content(json))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("타이틀을 입력해주세요."))
//                .andExpect(jsonPath("$.code").value("400"))
//                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
//                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
        Assertions.assertEquals(1L, postRepository.count());
    }

    @Test
    @DisplayName("3번 /posts 요청시 DB에 값이 저장된다.")
    void test3() throws Exception {  // 가능하면 application/json을 권장합니다. (기존 application/x-www-form-urlencoded)

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다\", \"content\": \"컨텐츠\"}"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //then
        Assertions.assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다", post.getTitle());
        assertEquals("제목입니다", post.getContent());
    }

}