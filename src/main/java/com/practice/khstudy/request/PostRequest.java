package com.practice.khstudy.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequest {

    @NotBlank(message = "타이틀을 입력해주세요.")
    String title;

    @NotBlank(message = "컨텐츠를 입력해주세요.")
    String content;
}
