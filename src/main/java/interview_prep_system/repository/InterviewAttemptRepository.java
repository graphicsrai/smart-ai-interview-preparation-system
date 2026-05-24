package interview_prep_system.repository;

import interview_prep_system.entity.InterviewAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterviewAttemptRepository
        extends JpaRepository<InterviewAttempt, Long> {

    @Query("SELECT AVG(i.score) FROM InterviewAttempt i")
    Double getAverageScore();

    @Query("SELECT MAX(i.score) FROM InterviewAttempt i")
    Integer getHighestScore();

    @Query("SELECT MIN(i.score) FROM InterviewAttempt i")
    Integer getLowestScore();
    @Query("""
       SELECT i.skill
       FROM InterviewAttempt i
       GROUP BY i.skill
       """)
    List<String> findAllSkills();
    @Query("""
       SELECT COUNT(i)
       FROM InterviewAttempt i
       WHERE i.skill = :skill
       """)
    long countBySkill(String skill);

    @Query("""
       SELECT AVG(i.score)
       FROM InterviewAttempt i
       WHERE i.skill = :skill
       """)
    Double getAverageScoreBySkill(String skill);

    List<InterviewAttempt> findTop10ByOrderByCreatedAtDesc();
}