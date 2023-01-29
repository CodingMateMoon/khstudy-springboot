package com.practice.khstudy.controller;

import com.practice.khstudy.request.PostRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    @GetMapping("/get")
    String get() {
        return "hello world";
    }

    @GetMapping("/gets")  //  google.com/?title=study&content=mvc연습
//    @RequestMapping(method = RequestMethod.GET, path ="/v1/posts")
    public String get(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hello";
        //test
    }

    @PostMapping("/posts")  //  google.com/?title=study&content=mvc연습
//    public String post(@RequestParam Map<String, String> params) {
    public Map<String,String> post(@RequestBody @Valid PostRequest postRequest) {
        log.info("postTest={}", postRequest);
        // {"title": "타이틀 값이 없습니다."}

        return Map.of();
    }



}
