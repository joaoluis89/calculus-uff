package com.example.calculus.controllers.jsons;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.text.Normalizer;

@With
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QuestionJson {

    @NotBlank
    private final String question;

    public String getQuestion() {
        return StringUtils.stripAccents(Normalizer.normalize(question, Normalizer.Form.NFD)).toLowerCase().trim();
    }

}
