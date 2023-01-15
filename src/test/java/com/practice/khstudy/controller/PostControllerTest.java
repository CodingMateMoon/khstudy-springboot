package com.practice.khstudy.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/get 요청시 Hello World를 출력한다.")
    void indexTest() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("param 테스트")
    void get테스트() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/paramtest")
                        .param("title","kh")
                        .param("content","study"))
                .andExpect(status().isOk())
                .andExpect(content().string("kh : study"))
                .andDo(MockMvcResultHandlers.print());

    }
}