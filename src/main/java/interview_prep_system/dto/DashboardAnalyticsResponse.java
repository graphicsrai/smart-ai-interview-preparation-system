package interview_prep_system.dto;

public class DashboardAnalyticsResponse {

    private long totalResumes;

    private long totalInterviewAttempts;

    private double averageInterviewScore;

    private int highestScore;

    private int lowestScore;

    public DashboardAnalyticsResponse(
            long totalResumes,
            long totalInterviewAttempts,
            double averageInterviewScore,
            int highestScore,
            int lowestScore) {

        this.totalResumes = totalResumes;
        this.totalInterviewAttempts = totalInterviewAttempts;
        this.averageInterviewScore = averageInterviewScore;
        this.highestScore = highestScore;
        this.lowestScore = lowestScore;
    }

    public long getTotalResumes() {
        return totalResumes;
    }

    public long getTotalInterviewAttempts() {
        return totalInterviewAttempts;
    }

    public double getAverageInterviewScore() {
        return averageInterviewScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public int getLowestScore() {
        return lowestScore;
    }
}