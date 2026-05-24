package interview_prep_system.dto;

import java.util.List;

public class ResumeRecommendationResponse {

    private String overallFeedback;

    private List<String> recommendations;

    public ResumeRecommendationResponse(
            String overallFeedback,
            List<String> recommendations) {

        this.overallFeedback = overallFeedback;
        this.recommendations = recommendations;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }
}