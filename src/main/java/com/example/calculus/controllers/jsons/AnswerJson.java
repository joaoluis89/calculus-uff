package com.example.calculus.controllers.jsons;

import lombok.*;

import javax.validation.constraints.NotBlank;

@With
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AnswerJson {

    @NotBlank
    private final String answer;
}
