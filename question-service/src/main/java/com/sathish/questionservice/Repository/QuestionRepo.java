package com.sathish.questionservice.Repository;

import com.sathish.questionservice.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q Where q.category=:categoryName ORDER BY RAND() LIMIT :numQuestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String categoryName,Integer numQuestions);
}
