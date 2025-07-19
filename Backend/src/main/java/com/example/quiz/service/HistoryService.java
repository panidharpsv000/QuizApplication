package com.example.quiz.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.repository.History;
import com.example.quiz.repository.HistoryRepo;
import com.example.quiz.repository.User;
import com.example.quiz.repository.UserRepo;

@Service
public class HistoryService {

	@Autowired
	HistoryRepo repo2;
	@Autowired
	UserRepo repo1;
	public void addHistory(long userId,History his) {
		his.setDate(LocalDateTime.of(2025,03,25,20,59,01));
		User u=repo1.findById(userId).orElse(null);
		//repo1.save(u);
		his.setUser(u);
		repo2.save(his);
		u.getHistory().add(his);
	}
	public List<History> getHistory(long id) {
		return repo2.findByUser(id);
	}
	public List<Object[]> getDefaultLeaderBoard() {
		return repo2.getDefaultLeaderBoardByTotalScore();
	}

	public List<Object[]> getCategoryAndDifficultyLeaderBoard(String category,String difficulty) {
		return repo2.getCategoryAndDifficultyLeaderBoardByTotalScore(category,difficulty);
	}
	public List<Object[]> getCategoryLeaderBoard(String category) {
		return repo2.getCategoryLeaderBoardByTotalScore(category);
	}

	public List<Object[]> getDifficultyLeaderBoard(String difficulty) {
		return repo2.getDifficultyLeaderBoardByTotalScore(difficulty);
	}
	
	public List<Object> getCategories()
    {
		 Object[] result=repo2.getCategories();
	     return new ArrayList<>(Arrays.asList(result));
	}

}
