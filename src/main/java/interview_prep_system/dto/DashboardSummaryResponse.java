package interview_prep_system.dto;

public class DashboardSummaryResponse {

    private long totalResumes;

    private int totalSkillsDetected;

    private int totalInterviewQuestionsGenerated;

    public DashboardSummaryResponse(
            long totalResumes,
            int totalSkillsDetected,
            int totalInterviewQuestionsGenerated) {

        this.totalResumes = totalResumes;
        this.totalSkillsDetected = totalSkillsDetected;
        this.totalInterviewQuestionsGenerated =
                totalInterviewQuestionsGenerated;
    }

    public long getTotalResumes() {
        return totalResumes;
    }

    public int getTotalSkillsDetected() {
        return totalSkillsDetected;
    }

    public int getTotalInterviewQuestionsGenerated() {
        return totalInterviewQuestionsGenerated;
    }
}