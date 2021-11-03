package com.example.calculus.domains;


import lombok.*;

import java.util.UUID;

@With
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(of = "id")
public class Answer {

    @Builder.Default
    private final String id = UUID.randomUUID().toString().replaceAll("-", "");
    private final String answer;
}
