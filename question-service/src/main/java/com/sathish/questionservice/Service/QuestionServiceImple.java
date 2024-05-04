package com.sathish.questionservice.Service;
import com.sathish.questionservice.Model.Question;
import com.sathish.questionservice.Model.QuestionWrapper;
import com.sathish.questionservice.Model.Response;
import com.sathish.questionservice.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImple implements QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
       try{
           return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
            List<Integer> questionIds=questionRepo.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questionIds,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIDs(List<Integer> questionIds) {
        List<Question> allQuestions=questionRepo.findAllById(questionIds);
        List<QuestionWrapper> wrappers=new ArrayList<>();
        for(Question q:allQuestions)
        {
            QuestionWrapper w=new QuestionWrapper();
            w.setId(q.getId());
            w.setQuestionTitle(q.getQuestionTitle());
            w.setOption1(q.getOption1());
            w.setOption2(q.getOption2());
            w.setOption3(q.getOption3());
            w.setOption4(q.getOption4());
            wrappers.add(w);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateScore(List<Response> responses) {

        int right=0;
        for(Response r:responses)
        {
            Question q=questionRepo.findById(r.getId()).get();
            if(r.getResponse().equals(q.getRightAnswer()))
            {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
