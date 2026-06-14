package interview_prep_system.dto;

import java.util.List;

public class JobMatchResponse {

    private int matchPercentage;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private List<String> suggestions;

    public JobMatchResponse(
            int matchPercentage,
            List<String> matchedSkills,
            List<String> missingSkills,
            List<String> suggestions) {

        this.matchPercentage = matchPercentage;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.suggestions = suggestions;
    }

    public int getMatchPercentage() {
        return matchPercentage;
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