package interview_prep_system.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    private static final List<String> SKILL_DICTIONARY = List.of(
            "Java",
            "Spring Boot",
            "Spring MVC",
            "Hibernate",
            "JPA",
            "React.js",
            "JavaScript",
            "HTML",
            "CSS",
            "Bootstrap",
            "MySQL",
            "Oracle",
            "PostgreSQL",
            "REST API",
            "Microservices",
            "Docker",
            "AWS",
            "Git",
            "Maven"
    );

    public List<String> extractSkills(String text) {

        List<String> foundSkills = new ArrayList<>();

        for (String skill : SKILL_DICTIONARY) {

            if (text.toLowerCase()
                    .contains(skill.toLowerCase())) {

                foundSkills.add(skill);
            }
        }

        return foundSkills;
    }

    public List<String> extractSkillsFromResume(String resumeText) {

        return extractSkills(resumeText);
    }
}