package interview_prep_system.dto;

import java.util.List;

public class AnswerEvaluationResponse {

    private int score;

    private String feedback;

    private List<String> strengths;

    private List<String> improvements;

    public AnswerEvaluationResponse(
            int score,
            String feedback,
            List<String> strengths,
            List<String> improvements) {

        this.score = score;
        this.feedback = feedback;
        this.strengths = strengths;
        this.improvements = improvements;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getImprovements() {
        return improvements;
    }
}