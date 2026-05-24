package interview_prep_system.dto;

public class SkillPerformanceResponse {

    private String skill;

    private long attempts;

    private double averageScore;

    public SkillPerformanceResponse(
            String skill,
            long attempts,
            double averageScore) {

        this.skill = skill;
        this.attempts = attempts;
        this.averageScore = averageScore;
    }

    public String getSkill() {
        return skill;
    }

    public long getAttempts() {
        return attempts;
    }

    public double getAverageScore() {
        return averageScore;
    }
}