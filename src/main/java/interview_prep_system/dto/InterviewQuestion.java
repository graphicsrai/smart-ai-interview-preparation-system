package interview_prep_system.dto;

public class InterviewQuestion {

    private String skill;

    private String difficulty;

    private String question;

    public InterviewQuestion(
            String skill,
            String difficulty,
            String question) {

        this.skill = skill;
        this.difficulty = difficulty;
        this.question = question;
    }

    public String getSkill() {
        return skill;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }
}