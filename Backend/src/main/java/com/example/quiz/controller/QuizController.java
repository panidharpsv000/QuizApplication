package com.example.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.repository.Question;
import com.example.quiz.service.QuizService;

@RestController
@RequestMapping("/api/quiz/questions")
public class QuizController {
	@Autowired
	QuizService service;


   @GetMapping("/allAnswers")
   public List<Question> getAllAnswers()
   {
	   return service.getAllAnswers();
   }

   @PostMapping("/addQuestion")
   public void addQuestion(@RequestBody List<Question> questions)
   {
	   for(Question question : questions)
	   service.addQuestion(question);
   }

   @GetMapping("/getCategories")
   public ResponseEntity<Map<String,List<Object>>> getCategories()
   {
	   Map<String,List<Object>> map=new HashMap<>();
	   map.put("categories", service.getCategories());
	   return ResponseEntity.ok(map);
   }

   @GetMapping("/checkOption/{id}/{choosen}")
   public boolean checkOption(@PathVariable int id,@PathVariable String choosen)
   {
	   return service.checkOption(id,choosen);
   }

   @GetMapping("/getQuestions/{category}/{difficulty}")
   public List<Question> getQuestions(@PathVariable String category,@PathVariable String difficulty)
   {
	   return service.getQuestions(category.toLowerCase(),difficulty.toLowerCase());
   }

}
