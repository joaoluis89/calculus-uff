package com.example.calculus.repositories;

import com.example.calculus.domains.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {

    Optional<Question> findOneByQuestion(String question);
}
