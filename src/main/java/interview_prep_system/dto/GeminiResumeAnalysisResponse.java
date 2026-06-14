package interview_prep_system.dto;

public class GeminiResumeAnalysisResponse {

    private String analysis;

    public GeminiResumeAnalysisResponse(
            String analysis) {

        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }
}