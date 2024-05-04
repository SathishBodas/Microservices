package com.sathish.questionservice.Service;

import com.sathish.questionservice.Model.Question;
import com.sathish.questionservice.Model.QuestionWrapper;
import com.sathish.questionservice.Model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    ResponseEntity<List<Question>> getAllQuestions();
    ResponseEntity<List<Question>> getQuestionByCategory(String category);
    ResponseEntity<String> addQuestion(Question question);

    ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions);

    ResponseEntity<List<QuestionWrapper>> getQuestionsFromIDs(List<Integer> questionIds);

    ResponseEntity<Integer> calculateScore(List<Response> responses);
}
