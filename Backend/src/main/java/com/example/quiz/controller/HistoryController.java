package com.example.quiz.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.repository.History;
import com.example.quiz.service.HistoryService;
@RestController
@RequestMapping("/api/quiz/history")
public class HistoryController {
	@Autowired
	HistoryService service;
	@GetMapping("/getHistory/{id}")
	public List<History> getHistory(@PathVariable long id)
	{
		return service.getHistory(id);
	}

	@PostMapping("/addHistory/{userId}")
	public String addHistory(@RequestBody History his ,@PathVariable long userId )
	{
		service.addHistory(userId,his);
		System.out.println("hello");
		 return "sucess";
	}

	@GetMapping("/getDefaultLeaderBoard")
	public ResponseEntity<List<Object[]>> getLeaderBoard()
	{
		List<Object[]> result=service.getDefaultLeaderBoard();
		//System.out.println("Hai");
		return ResponseEntity.ok(result);
	}

    private List<Map<String,Object>> getResponse(List<Object[]> results)
    {
    	List<Map<String, Object>> response = new ArrayList();
    	for (Object[] row : results) {
            Map<String, Object> data = new HashMap();
            data.put("UserId", row[0]);
            data.put("UserName", row[1]);
            data.put("Category", row[2]);
            data.put("TotalScore", row[3]);
            response.add(data);
    	}
    	return response;
    }

	@GetMapping("/getCategoryAndDifficultyLeaderBoard/{category}/{difficulty}") // ?category="java"&difficulty="easy"
	public ResponseEntity<List<Object[]>> getCategoryAndDifficultyLeaderBoard(@PathVariable String category ,@PathVariable String difficulty)
	{
		System.out.println(category+difficulty);
		return ResponseEntity.ok(service.getCategoryAndDifficultyLeaderBoard(category,difficulty));
	}

	@GetMapping("/getCategoryLeaderBoard/{category}") // ?category="java"
	public ResponseEntity<List<Object[]>> getCategoryLeaderBoard(@PathVariable String category)
	{
		List<Object[]> result=service.getCategoryLeaderBoard(category);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/getDifficultyLeaderBoard/{difficulty}") // ?difficulty="easy"
	public ResponseEntity<List<Object[]>> getDifficultyLeaderBoard(@PathVariable String difficulty)
	{
		System.out.println(difficulty);
		return ResponseEntity.ok(service.getDifficultyLeaderBoard(difficulty));
	}
	
	@GetMapping("/getCategories")
	 public ResponseEntity<Map<String,List<Object>>> getCategories()
	 {
         Map<String,List<Object>> map=new HashMap<>();
         map.put("categories", service.getCategories());
         return ResponseEntity.ok(map);
	 }
}
