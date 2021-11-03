package com.example.calculus.controllers;

import com.example.calculus.controllers.jsons.AnswerJson;
import com.example.calculus.controllers.jsons.QuestionAlreadyExistsJson;
import com.example.calculus.controllers.jsons.QuestionJson;
import com.example.calculus.domains.Question;
import com.example.calculus.repositories.QuestionRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

    @PostMapping
    public ResponseEntity createQuestion(
            @RequestHeader(defaultValue = " false") boolean forceSave,
            @RequestBody @Valid final QuestionJson questionJson
    ) {

        if (forceSave) {
            return ResponseEntity.ok().body(questionRepository.save(Question.builder()
                            .question(questionJson.getQuestion())
                    .build()));
        } else {
            val oneByQuestion = questionRepository.findOneByQuestion(
                    questionJson.getQuestion()
            );

            if (oneByQuestion.isPresent()) {
                return ResponseEntity.badRequest().body(
                        QuestionAlreadyExistsJson.builder()
                                .questionInDataBase(oneByQuestion.get().getQuestion())
                                .build());

            } else {
                return ResponseEntity.ok().body(questionRepository.save(Question.builder()
                        .question(questionJson.getQuestion())
                        .build()));
            }
        }
    }

    @PutMapping("/{questionId}/answer")
    public ResponseEntity addAnswer(
            @PathVariable final String questionId,
            @RequestBody @Valid final AnswerJson answerJson
    ) {
        val question = questionRepository.findById(questionId);

        if (question.isPresent()) {
            return ResponseEntity.ok().body(questionRepository.save(question.get().addAnswer(answerJson.getAnswer())));
        } else {
            return ResponseEntity.badRequest().body("Question not found");
        }
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(
            @PathVariable final String questionId
    ) {
        questionRepository.deleteById(questionId);
    }

    @DeleteMapping("/{questionId}/answer/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(
            @PathVariable final String questionId,
            @PathVariable final String answerId
    ) {
        questionRepository.findById(questionId)
                .map(question -> question.removeAnswer(answerId))
                .map(questionRepository::save)
                .orElseThrow();
        ;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Question> getAllQuestions(
            @RequestParam final String questionId
    ) {
        return questionRepository.findById(questionId);
    }
}
