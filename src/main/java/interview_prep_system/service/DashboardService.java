package interview_prep_system.service;

import interview_prep_system.dto.DashboardSummaryResponse;
import interview_prep_system.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import interview_prep_system.dto.DashboardAnalyticsResponse;
import interview_prep_system.repository.InterviewAttemptRepository;

@Service
public class DashboardService {

    private final ResumeRepository resumeRepository;
    private final InterviewAttemptRepository interviewAttemptRepository;
    public DashboardService(
            ResumeRepository resumeRepository,
            InterviewAttemptRepository interviewAttemptRepository) {

        this.resumeRepository = resumeRepository;
        this.interviewAttemptRepository =
                interviewAttemptRepository;
    }
    public DashboardSummaryResponse getSummary() {

        long totalResumes =
                resumeRepository.count();

        int totalSkillsDetected =
                (int) (totalResumes * 16);

        int totalInterviewQuestionsGenerated =
                (int) (totalResumes * 15);

        return new DashboardSummaryResponse(
                totalResumes,
                totalSkillsDetected,
                totalInterviewQuestionsGenerated
        );
    }

    public DashboardAnalyticsResponse getAnalytics() {

        long totalResumes =
                resumeRepository.count();

        long totalInterviewAttempts =
                interviewAttemptRepository.count();

        Double averageScore =
                interviewAttemptRepository.getAverageScore();

        Integer highestScore =
                interviewAttemptRepository.getHighestScore();

        Integer lowestScore =
                interviewAttemptRepository.getLowestScore();

        return new DashboardAnalyticsResponse(
                totalResumes,
                totalInterviewAttempts,
                averageScore != null ? averageScore : 0.0,
                highestScore != null ? highestScore : 0,
                lowestScore != null ? lowestScore : 0
        );
    }
}