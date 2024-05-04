package com.sathish.quizservice.Model;

import lombok.Data;

@Data
public class QuizDto {
    private String categoryName;
    private Integer numQuestions;
    private  String title;
}
