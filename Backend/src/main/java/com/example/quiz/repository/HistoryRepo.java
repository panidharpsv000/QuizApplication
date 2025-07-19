package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepo extends JpaRepository<History,Long>{
	@Query("SELECT h.user.id, MIN(h.user.userName),COUNT(h.user.id) AS attended,SUM(h.score) AS score FROM History h GROUP BY h.user.id  ORDER BY score DESC")
	public List<Object[]> getDefaultLeaderBoardByTotalScore();
	@Query("SELECT h.user.id, MIN(h.user.userName),COUNT(h.user.id) AS attended,SUM(h.score) AS score FROM History h WHERE h.category=:category GROUP BY h.user.id  ORDER BY score DESC")
	public List<Object[]> getCategoryLeaderBoardByTotalScore(String category);
	@Query("SELECT h.user.id, MIN(h.user.userName),COUNT(h.user.id) AS attended,SUM(h.score) AS score FROM History h WHERE h.difficulty=:difficulty GROUP BY h.user.id  ORDER BY score DESC")
	public List<Object[]> getDifficultyLeaderBoardByTotalScore(String difficulty);
	@Query("SELECT h.user.id, MIN(h.user.userName),MIN(h.category),SUM(h.score) AS score FROM History h WHERE h.category=:category AND h.difficulty=:difficulty GROUP BY h.user.id  ORDER BY score DESC")
	public List<Object[]> getCategoryAndDifficultyLeaderBoardByTotalScore(String category,String difficulty);
    @Query("SELECT SUM(score) FROM History WHERE user.id=:id")
    public Long findSumById(Long id);
    @Query("SELECT COUNT(score) FROM History WHERE user.id=:id")
    public Long findCountById(Long id);
    @Query("SELECT h FROM History h WHERE h.user.id=:id ORDER BY h.date")
    public List<History> findByUser(Long id);
    @Query("SELECT DISTINCT category FROM History")
    public Object[] getCategories();
}
