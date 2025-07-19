package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Question, Long> {
   public List<Question> findByCategory(String category);
   public List<Question> findByDifficulty(String difficulty);
   public List<Question> findByCategoryAndDifficulty(String category,String difficulty);
   @Query("SELECT DISTINCT category FROM Question")
   public Object[] getCategories();
}
