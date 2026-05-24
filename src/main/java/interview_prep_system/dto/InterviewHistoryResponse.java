package interview_prep_system.dto;

import java.time.LocalDateTime;

public class InterviewHistoryResponse {

    private String skill;

    private String difficulty;

    private Integer score;

    private LocalDateTime createdAt;

    public InterviewHistoryResponse(
            String skill,
            String difficulty,
            Integer score,
            LocalDateTime createdAt) {

        this.skill = skill;
        this.difficulty = difficulty;
        this.score = score;
        this.createdAt = createdAt;
    }

    public String getSkill() {
        return skill;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getScore() {
        return score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}