package com.practice.khstudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostController {

    @GetMapping("/get")
    String get() {
        return "hello world";
    }


    @GetMapping("/paramtest")  //  google.com/?title=study&content=mvc연습
//    @RequestMapping(method = RequestMethod.GET, path ="/v1/posts")
    public String get2(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        System.out.println(title + content);
        return title + " : " + content;
        //test
    }


}
