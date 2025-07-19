package com.example.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.repository.HistoryRepo;
import com.example.quiz.repository.User;
import com.example.quiz.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo repo;

	@Autowired
	HistoryRepo hisRepo;
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public Map<String,String> addUser(User user)
	{
		User u=repo.save(user);
		Map<String,String> map=new HashMap();
		System.out.println(100);
		map.put("id",u.getId()+"");
		map.put("userName", u.getUserName());
		return map;
	}
	
	public Map<String,String> login(User user)
	{
		Map<String,String>map=new HashMap();
		long id=repo.findId(user.getMobile(),user.getPassword());
		User u=repo.findById(id).orElse(null);
		if(repo.existsByMobileAndPassword(user.getMobile(),user.getPassword()))
		{
			map.put("id", id+"");
			map.put("userName", u.getUserName());
		}
		else
		{
			map.put("id", null);
			map.put("userName", null);
		}
		System.out.println(map);
		return map;
	}
	
	public void updateUser(User user) {
		User old=repo.findById(user.getId()).orElse(null);
		user.setPassword(old.getPassword());
		user.setHistory(old.getHistory());
		repo.save(user);
	}
    public List<Map<String,Object>> getUserData(int id)
    {
    	User user=repo.findById((long)id).orElse(null);
    	List<Map<String,Object>> response=new ArrayList<>();
    	Map<String,Object> map=new HashMap<>();
    	map.put("userName", user.getUserName());
    	map.put("email", user.getEmail());
    	map.put("id", user.getId());
    	map.put("mobile", user.getMobile());
    	Long score=hisRepo.findSumById((long)id);
    	Long attended=hisRepo.findCountById((long)id);
    	map.put("score",(score));
    	map.put("attended", attended);
    	response.add(map);
    	return response;
    }

}
