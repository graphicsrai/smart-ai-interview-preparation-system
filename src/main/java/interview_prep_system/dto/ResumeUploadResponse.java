package interview_prep_system.dto;

import java.util.List;

public class ResumeUploadResponse {

    private Long id;

    private String fileName;

    private String status;

    private int atsScore;

    private String rating;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private List<String> suggestions;

    public ResumeUploadResponse(
            Long id,
            String fileName,
            String status,
            int atsScore,
            String rating,
            List<String> matchedSkills,
            List<String> missingSkills,
            List<String> suggestions) {

        this.id = id;
        this.fileName = fileName;
        this.status = status;
        this.atsScore = atsScore;
        this.rating = rating;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.suggestions = suggestions;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStatus() {
        return status;
    }

    public int getAtsScore() {
        return atsScore;
    }

    public String getRating() {
        return rating;
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