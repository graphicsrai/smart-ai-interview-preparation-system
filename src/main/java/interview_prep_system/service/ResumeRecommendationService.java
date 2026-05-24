package interview_prep_system.service;

import interview_prep_system.dto.ResumeRecommendationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeRecommendationService {

    public ResumeRecommendationResponse generateRecommendations(
            int atsScore,
            List<String> detectedSkills,
            List<String> missingSkills) {

        List<String> recommendations = new ArrayList<>();

        String overallFeedback;

        if (atsScore >= 90) {
            overallFeedback =
                    "Strong resume with excellent technical profile and industry experience.";
        } else if (atsScore >= 75) {
            overallFeedback =
                    "Good resume with some opportunities for improvement.";
        } else {
            overallFeedback =
                    "Resume needs improvement to become more competitive.";
        }

        if (missingSkills.contains("Docker")) {
            recommendations.add(
                    "Add Docker experience to demonstrate containerization skills.");
        }

        if (missingSkills.contains("AWS")) {
            recommendations.add(
                    "Include AWS or Azure projects to strengthen cloud expertise.");
        }

        if (missingSkills.contains("Maven")) {
            recommendations.add(
                    "Mention Maven or Gradle build tools in technical skills.");
        }

        if (!detectedSkills.contains("Git")) {
            recommendations.add(
                    "Add version control experience such as Git and GitHub.");
        }

        recommendations.add(
                "Include measurable achievements such as performance improvements or delivery metrics.");

        recommendations.add(
                "Provide GitHub project links to strengthen your technical profile.");

        return new ResumeRecommendationResponse(
                overallFeedback,
                recommendations
        );
    }
}