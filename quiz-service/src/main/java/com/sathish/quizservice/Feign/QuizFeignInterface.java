package com.sathish.quizservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.sathish.quizservice.Model.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizFeignInterface {

    @GetMapping("question/generateQuiz") //for quiz service no need to know questions it needs only question ids by that it can fetch questions
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam(name = "categoryName") String categoryName, @RequestParam("numOfQuestions") Integer numQuestions);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
