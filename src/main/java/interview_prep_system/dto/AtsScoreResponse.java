package interview_prep_system.dto;

import java.util.List;

public class AtsScoreResponse {

    private int atsScore;
    private String rating;
    private List<String> matchedSkills;

    private List<String> missingSkills;

    private List<String> suggestions;

    public AtsScoreResponse(
            int atsScore,
            String rating,
            List<String> matchedSkills,
            List<String> missingSkills,
            List<String> suggestions) {

        this.atsScore = atsScore;
        this.rating = rating;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.suggestions = suggestions;
    }
    public String getRating() {
        return rating;
    }
    public int getAtsScore() {
        return atsScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}