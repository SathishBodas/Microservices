package com.sathish.quizservice.Controller;

import com.sathish.quizservice.Model.QuestionWrapper;
import com.sathish.quizservice.Model.QuizDto;
import com.sathish.quizservice.Model.Response;
import com.sathish.quizservice.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }
    @GetMapping("/getQuiz/{id}")
    public  ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") Integer id)
    {
        return quizService.getQuiz(id);
    }

    @GetMapping("/submit/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable("id") Integer id,@RequestBody List<Response> responses)
    {
        return quizService.calculateScore(id,responses);
    }

}
