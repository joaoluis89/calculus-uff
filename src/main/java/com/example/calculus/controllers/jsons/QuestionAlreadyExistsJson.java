package com.example.calculus.controllers.jsons;

import lombok.*;

@With
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QuestionAlreadyExistsJson {
    private final String message = "Existe uma questão igual ou parecida no banco. Avalie, " +
            "caso sejam diferentes realize nova chamada com force true no header";
    private final String questionInDataBase;
}
