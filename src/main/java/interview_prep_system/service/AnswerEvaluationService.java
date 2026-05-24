package interview_prep_system.service;

import interview_prep_system.dto.AnswerEvaluationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import interview_prep_system.entity.InterviewAttempt;
import interview_prep_system.repository.InterviewAttemptRepository;
import java.time.LocalDateTime;

@Service
public class AnswerEvaluationService {
    private final InterviewAttemptRepository interviewAttemptRepository;
    public AnswerEvaluationService(
            InterviewAttemptRepository interviewAttemptRepository) {

        this.interviewAttemptRepository =
                interviewAttemptRepository;
    }
    public AnswerEvaluationResponse evaluateAnswer(
            String skill,
            String difficulty,
            String question,
            String answer) {

        int score = 0;

        List<String> strengths = new ArrayList<>();
        List<String> improvements = new ArrayList<>();

        if (answer != null && answer.length() > 50) {
            score += 4;
            strengths.add(
                    "Answer contains a reasonable level of detail.");
        } else {
            improvements.add(
                    "Provide a more detailed explanation.");
        }

        if (answer != null && answer.length() > 100) {
            score += 3;
            strengths.add(
                    "Answer demonstrates good elaboration.");
        }

        if (answer != null &&
                answer.toLowerCase().contains(skill.toLowerCase())) {

            score += 3;

            strengths.add(
                    "Answer references the relevant technology.");
        } else {

            improvements.add(
                    "Reference the relevant technology explicitly.");
        }

        if (score > 10) {
            score = 10;
        }

        String feedback;

        if (score >= 8) {

            feedback =
                    "Good answer. Covers important concepts with reasonable detail.";

        } else if (score >= 5) {

            feedback =
                    "Average answer. Additional explanation would improve clarity.";

        } else {

            feedback =
                    "Weak answer. Expand the explanation and include key concepts.";
        }
        InterviewAttempt attempt =
                new InterviewAttempt();

        attempt.setSkill(skill);
        attempt.setDifficulty(difficulty);
        attempt.setQuestion(question);
        attempt.setAnswer(answer);
        attempt.setScore(score);
        attempt.setFeedback(feedback);
        attempt.setCreatedAt(LocalDateTime.now());

        interviewAttemptRepository.save(attempt);
        return new AnswerEvaluationResponse(
                score,
                feedback,
                strengths,
                improvements
        );
    }
}