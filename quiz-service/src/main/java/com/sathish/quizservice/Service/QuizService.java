package com.sathish.quizservice.Service;

import com.sathish.quizservice.Model.QuestionWrapper;
import com.sathish.quizservice.Model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

    ResponseEntity<Integer> getScore(Integer id, List<Response> responses);

    ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id);

    ResponseEntity<Integer> calculateScore(Integer id,List<Response> responses);
}
