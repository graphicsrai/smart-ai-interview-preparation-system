package interview_prep_system.repository;

import interview_prep_system.entity.InterviewAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewAttemptRepository
        extends JpaRepository<InterviewAttempt, Long> {

    @Query("SELECT AVG(i.score) FROM InterviewAttempt i")
    Double getAverageScore();

    @Query("SELECT MAX(i.score) FROM InterviewAttempt i")
    Integer getHighestScore();

    @Query("SELECT MIN(i.score) FROM InterviewAttempt i")
    Integer getLowestScore();
}