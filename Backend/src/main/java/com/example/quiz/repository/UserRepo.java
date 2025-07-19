package com.example.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	@Query("SELECT (id) FROM User WHERE mobile=:mobile AND password=:password")
	public Long findId(String mobile,String password);
	@Query("SELECT (id) FROM User ")
	public Long findId();
	public boolean existsByMobileAndPassword(String mobile,String password);

}
