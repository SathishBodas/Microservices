package com.sathish.quizservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String title;

    @ElementCollection //@ManyToMany is used for entities but @ElmentCollection is used integer data
    private List<Integer> questionIds;
}
