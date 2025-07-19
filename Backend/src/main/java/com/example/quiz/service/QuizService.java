package com.example.quiz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.repository.HistoryRepo;
import com.example.quiz.repository.Question;
import com.example.quiz.repository.QuizRepo;
import com.example.quiz.repository.UserRepo;

@Service
public class QuizService {

	@Autowired
	QuizRepo repo;

	@Autowired
	HistoryRepo repo2;

	@Autowired
	UserRepo repo1;
   public List<Question> getAllAnswers()
   {
	   return repo.findAll();
   }

   public List<Object> getCategories()
   {
	   Object[] result=repo.getCategories();
	   return new ArrayList<>(Arrays.asList(result));
   }

   public void addQuestion(Question question)
   {
	   repo.save(question);
   }

   public List<Question> getByCategory(String category) {
   	   return repo.findByCategory(category);
   }

   public List<Question> getByDifficulty(String difficulty) {
   	  return  repo.findByDifficulty(difficulty);
   }

   public List<Question> getQuestions(String category, String difficulty) {
	     return repo.findByCategoryAndDifficulty(category,difficulty);
   }

   public boolean checkOption(int id,String choosen) {
	   Question question=repo.findById((long)id).orElse(null);
	   return question.getCorrectAnswer().equals(choosen);
   }
}
