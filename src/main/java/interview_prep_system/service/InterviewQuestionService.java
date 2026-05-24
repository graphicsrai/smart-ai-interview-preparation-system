package interview_prep_system.service;

import interview_prep_system.dto.InterviewQuestion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Map;

@Service
public class InterviewQuestionService {
    private static final Map<String, List<InterviewQuestion>> QUESTION_BANK =
            Map.of(

                    "Java", List.of(
                            new InterviewQuestion("Java", "Easy",
                                    "What are the main principles of OOP?"),

                            new InterviewQuestion("Java", "Medium",
                                    "What is the difference between HashMap and ConcurrentHashMap?"),

                            new InterviewQuestion("Java", "Medium",
                                    "What is the difference between ArrayList and LinkedList?"),

                            new InterviewQuestion("Java", "Hard",
                                    "Explain JVM Memory Management."),

                            new InterviewQuestion("Java", "Hard",
                                    "What is the difference between Comparable and Comparator?")
                    ),

                    "Spring Boot", List.of(
                            new InterviewQuestion("Spring Boot", "Easy",
                                    "What is Spring Boot?"),

                            new InterviewQuestion("Spring Boot", "Medium",
                                    "Explain Dependency Injection."),

                            new InterviewQuestion("Spring Boot", "Medium",
                                    "What is Spring Bean Lifecycle?"),

                            new InterviewQuestion("Spring Boot", "Hard",
                                    "Explain Auto Configuration."),

                            new InterviewQuestion("Spring Boot", "Hard",
                                    "What is the difference between @Component and @Bean?")
                    ),

                    "React.js", List.of(
                            new InterviewQuestion("React.js", "Easy",
                                    "What is JSX?"),

                            new InterviewQuestion("React.js", "Medium",
                                    "What is useEffect?"),

                            new InterviewQuestion("React.js", "Medium",
                                    "Difference between State and Props?"),

                            new InterviewQuestion("React.js", "Hard",
                                    "What is Virtual DOM?"),

                            new InterviewQuestion("React.js", "Hard",
                                    "Explain React Reconciliation.")
                    ),

                    "MySQL", List.of(
                            new InterviewQuestion("MySQL", "Easy",
                                    "What is a primary key?"),

                            new InterviewQuestion("MySQL", "Medium",
                                    "What is normalization?"),

                            new InterviewQuestion("MySQL", "Medium",
                                    "What are joins in SQL?"),

                            new InterviewQuestion("MySQL", "Hard",
                                    "What is indexing?"),

                            new InterviewQuestion("MySQL", "Hard",
                                    "Explain ACID properties.")
                    ),

                    "Microservices", List.of(
                            new InterviewQuestion("Microservices", "Easy",
                                    "What are microservices?"),

                            new InterviewQuestion("Microservices", "Medium",
                                    "Advantages of microservices?"),

                            new InterviewQuestion("Microservices", "Medium",
                                    "What is service discovery?"),

                            new InterviewQuestion("Microservices", "Hard",
                                    "Challenges in microservice architecture?"),

                            new InterviewQuestion("Microservices", "Hard",
                                    "Explain circuit breaker pattern.")
                    )
            );
    public List<InterviewQuestion> generateQuestions(
            List<String> skills) {

        List<InterviewQuestion> selectedQuestions =
                new ArrayList<>();

        for (String skill : skills) {

            if (!QUESTION_BANK.containsKey(skill)) {
                continue;
            }

            List<InterviewQuestion> skillQuestions =
                    new ArrayList<>(QUESTION_BANK.get(skill));

            Collections.shuffle(skillQuestions);

            int limit = Math.min(3,
                    skillQuestions.size());

            selectedQuestions.addAll(
                    skillQuestions.subList(0, limit));
        }

        Collections.shuffle(selectedQuestions);

        return selectedQuestions;
    }
}