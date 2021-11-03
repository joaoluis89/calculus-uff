package com.example.calculus.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.ArrayList;
import java.util.List;

@With
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Question {

    @Id
    private final String id;

    @Indexed
    @TextIndexed
    private final String question;

    @Builder.Default
    private final List<Answer> answers = new ArrayList<>();

    public Question addAnswer(final String answer) {
        this.answers.add(Answer.builder()
                        .answer(answer)
                .build());
        return this;
    }

    public Question removeAnswer(final String answerId) {
        this.answers.removeIf(answer -> answer.getId().equals(answerId));
        return this;
    }

}
