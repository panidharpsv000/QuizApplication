package com.example.quiz.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class History {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private int score;
    private String category;
    private String difficulty;
    private LocalDateTime date;
    @ManyToOne
    private User user;
}
