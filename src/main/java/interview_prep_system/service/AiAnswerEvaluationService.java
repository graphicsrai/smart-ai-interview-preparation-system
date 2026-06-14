package interview_prep_system.service;

import interview_prep_system.dto.AiAnswerEvaluationResponse;
import interview_prep_system.entity.InterviewAttempt;
import interview_prep_system.repository.InterviewAttemptRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AiAnswerEvaluationService {

    private final GeminiService geminiService;

    private final InterviewAttemptRepository
            interviewAttemptRepository;

    public AiAnswerEvaluationService(
            GeminiService geminiService,
            InterviewAttemptRepository interviewAttemptRepository) {

        this.geminiService = geminiService;
        this.interviewAttemptRepository =
                interviewAttemptRepository;
    }

    public AiAnswerEvaluationResponse evaluate(
            String skill,
            String difficulty,
            String question,
            String answer)
            throws Exception {

        String aiResponse =
                geminiService.evaluateAnswer(
                        skill,
                        difficulty,
                        question,
                        answer);

        ObjectMapper objectMapper =
                new ObjectMapper();

        JsonNode root =
                objectMapper.readTree(aiResponse);

        int score =
                root.path("score").asInt();

        String feedback =
                root.path("feedback").asText();

        List<String> strengths =
                objectMapper.convertValue(
                        root.path("strengths"),
                        new TypeReference<List<String>>() {
                        }
                );

        List<String> improvements =
                objectMapper.convertValue(
                        root.path("improvements"),
                        new TypeReference<List<String>>() {
                        }
                );

        InterviewAttempt attempt =
                new InterviewAttempt();

        attempt.setSkill(skill);
        attempt.setDifficulty(difficulty);
        attempt.setQuestion(question);
        attempt.setAnswer(answer);
        attempt.setScore(score);
        attempt.setFeedback(feedback);
        attempt.setCreatedAt(
                LocalDateTime.now());

        interviewAttemptRepository.save(
                attempt);

        return new AiAnswerEvaluationResponse(
                score,
                feedback,
                strengths,
                improvements
        );
    }
}