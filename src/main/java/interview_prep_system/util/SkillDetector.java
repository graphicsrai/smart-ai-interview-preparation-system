package interview_prep_system.util;

import java.util.ArrayList;
import java.util.List;

public class SkillDetector {

    private static final List<String> SKILLS = List.of(
            "Java",
            "Spring Boot",
            "Spring MVC",
            "JPA",
            "Hibernate",
            "React",
            "JavaScript",
            "HTML",
            "CSS",
            "Bootstrap",
            "SQL",
            "Docker",
            "AWS",
            "Microservices"
    );

    public static List<String> detectSkills(
            String resumeText) {

        List<String> detectedSkills =
                new ArrayList<>();

        String text =
                resumeText.toLowerCase();

        for (String skill : SKILLS) {

            if (text.contains(
                    skill.toLowerCase())) {

                detectedSkills.add(skill);
            }
        }

        return detectedSkills;
    }

    public static List<String> getAllSkills() {

        return SKILLS;
    }
}