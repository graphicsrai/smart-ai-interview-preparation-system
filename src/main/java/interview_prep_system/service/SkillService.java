package interview_prep_system.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
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
    private static final Map<String, List<String>> SKILL_ALIASES = Map.of(
            "REST API", List.of(
                    "REST API",
                    "REST",
                    "REST Calls",
                    "Web Services"
            ),
            "React.js", List.of(
                    "React.js",
                    "React",
                    "Axios",
                    "Fetch"
            )
    );
    public List<String> extractSkills(String text) {

        Set<String> foundSkills = new LinkedHashSet<>();

        String lowerText = text.toLowerCase();

        // Existing skill dictionary matching
        for (String skill : SKILL_DICTIONARY) {

            if (lowerText.contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        // Alias matching
        for (Map.Entry<String, List<String>> entry
                : SKILL_ALIASES.entrySet()) {

            String standardSkill = entry.getKey();

            for (String alias : entry.getValue()) {

                if (lowerText.contains(alias.toLowerCase())) {

                    foundSkills.add(standardSkill);
                    break;
                }
            }
        }

        return new ArrayList<>(foundSkills);
    }

    public List<String> extractSkillsFromResume(String resumeText) {

        return extractSkills(resumeText);
    }
}