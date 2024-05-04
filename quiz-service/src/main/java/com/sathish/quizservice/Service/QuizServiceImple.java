package com.sathish.quizservice.Service;

import com.sathish.quizservice.Feign.QuizFeignInterface;
import com.sathish.quizservice.Model.QuestionWrapper;
import com.sathish.quizservice.Model.Quiz;
import com.sathish.quizservice.Model.Response;
import com.sathish.quizservice.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImple implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizFeignInterface quizFeignInterface;
    @Override
    public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {
        List<Integer> questionIds=quizFeignInterface.getQuestionsForQuiz(categoryName,numQuestions).getBody(); // method returns response Entity
                                                                                                        // to get list<integer> we use get Body()
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);


        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {

        Quiz quiz=quizRepository.findById(id).get();
        List<Integer> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions=quizFeignInterface.getQuestionsFromIds(questionIds);
        return questions;
    }

    @Override
    public ResponseEntity<Integer> getScore(Integer id, List<Response> responses) {
        return null;
    }



    @Override
    public ResponseEntity<Integer> calculateScore(Integer id,List<Response> responses) {
        return quizFeignInterface.getScore(responses);
    }
}
