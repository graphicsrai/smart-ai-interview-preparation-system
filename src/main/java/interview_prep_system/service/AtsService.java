package interview_prep_system.service;

import interview_prep_system.dto.AtsScoreResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtsService {

    private static final List<String> RECOMMENDED_SKILLS = List.of(
            "Java",
            "Spring Boot",
            "React.js",
            "MySQL",
            "Microservices",
            "Docker",
            "AWS",
            "Git",
            "Maven",
            "REST API"
    );

    public AtsScoreResponse calculateScore(
            String resumeText,
            List<String> detectedSkills) {

        int score = 0;

        // Skills score (max 60)
        int skillCount = detectedSkills.size();

        if (skillCount >= 10) {
            score += 60;
        } else if (skillCount >= 8) {
            score += 50;
        } else if (skillCount >= 6) {
            score += 40;
        } else if (skillCount >= 4) {
            score += 30;
        } else {
            score += 20;
        }

        // Experience section
        if (resumeText.toUpperCase().contains("EXPERIENCE")) {
            score += 20;
        }

        // Education section
        if (resumeText.toUpperCase().contains("EDUCATION")) {
            score += 10;
        }

        // Projects section
        if (resumeText.toUpperCase().contains("PROJECT")
                || resumeText.toUpperCase().contains("PROJECTS")) {
            score += 10;
        }

        List<String> missingSkills = new ArrayList<>();

        for (String skill : RECOMMENDED_SKILLS) {

            if (!detectedSkills.contains(skill)) {
                missingSkills.add(skill);
            }
        }

        List<String> suggestions = new ArrayList<>();

        if (missingSkills.contains("Docker")) {
            suggestions.add("Add Docker experience");
        }

        if (missingSkills.contains("AWS")) {
            suggestions.add("Add cloud platform skills");
        }

        if (missingSkills.contains("REST API")) {
            suggestions.add("Highlight REST API experience");
        }

        String rating;

        if (score >= 90) {
            rating = "Excellent";
        } else if (score >= 75) {
            rating = "Good";
        } else if (score >= 60) {
            rating = "Average";
        } else {
            rating = "Needs Improvement";
        }
        return new AtsScoreResponse(
                score,
                rating,
                detectedSkills,
                missingSkills,
                suggestions
        );
    }
}