package interview_prep_system.dto;

import java.util.List;

public class JobInterviewQuestionResponse {

    private int totalQuestions;

    private int skillsCovered;

    private List<InterviewQuestion> questions;

    public JobInterviewQuestionResponse(
            int totalQuestions,
            int skillsCovered,
            List<InterviewQuestion> questions) {

        this.totalQuestions = totalQuestions;
        this.skillsCovered = skillsCovered;
        this.questions = questions;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getSkillsCovered() {
        return skillsCovered;
    }

    public List<InterviewQuestion> getQuestions() {
        return questions;
    }
}