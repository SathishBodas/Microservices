package com.sathish.questionservice.Controller;

import com.sathish.questionservice.Model.Question;
import com.sathish.questionservice.Model.QuestionWrapper;
import com.sathish.questionservice.Model.Response;
import com.sathish.questionservice.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment env; // to know port we use environment
                    // do import from org.springframework.core.env.Environment
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }
    @GetMapping("/generateQuiz") //for quiz service no need to know questions it needs only question ids by that it can fetch questions
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam(name = "categoryName") String categoryName,@RequestParam("numOfQuestions") Integer numQuestions)
    {
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds)
    {
        System.out.println(env.getProperty("local.server.port")); // to print on which port it getting called
        return questionService.getQuestionsFromIDs(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.calculateScore(responses);
    }

}
