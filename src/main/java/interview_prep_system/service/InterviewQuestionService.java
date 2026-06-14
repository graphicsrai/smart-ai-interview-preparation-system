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

                    "React", List.of(
                            new InterviewQuestion("React", "Easy",
                                    "What is JSX?"),

                            new InterviewQuestion("React", "Medium",
                                    "What is useEffect?"),

                            new InterviewQuestion("React", "Medium",
                                    "Difference between State and Props?"),

                            new InterviewQuestion("React", "Hard",
                                    "What is Virtual DOM?"),

                            new InterviewQuestion("React", "Hard",
                                    "Explain React Reconciliation.")
                    ),

                    "SQL", List.of(
                            new InterviewQuestion("SQL", "Easy",
                                    "What is a primary key?"),

                            new InterviewQuestion("SQL", "Medium",
                                    "What is normalization?"),

                            new InterviewQuestion("SQL", "Medium",
                                    "What are joins in SQL?"),

                            new InterviewQuestion("SQL", "Hard",
                                    "What is indexing?"),

                            new InterviewQuestion("SQL", "Hard",
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
                    ),
                    "JPA", List.of(
                            new InterviewQuestion("JPA", "Easy",
                                    "What is JPA?"),

                            new InterviewQuestion("JPA", "Medium",
                                    "Difference between JPA and Hibernate?"),

                            new InterviewQuestion("JPA", "Hard",
                                    "What is the N+1 query problem?")
                    ),
                    "JavaScript", List.of(
                            new InterviewQuestion("JavaScript", "Easy",
                                    "What is hoisting?"),

                            new InterviewQuestion("JavaScript", "Medium",
                                    "Difference between var, let and const?"),

                            new InterviewQuestion("JavaScript", "Hard",
                                    "Explain closures.")
                    ),
                    "HTML", List.of(
                            new InterviewQuestion("HTML", "Easy",
                                    "What are semantic tags?"),

                            new InterviewQuestion("HTML", "Medium",
                                    "Difference between div and span?"),

                            new InterviewQuestion("HTML", "Hard",
                                    "Explain local storage and session storage.")
                    ),"CSS", List.of(
                            new InterviewQuestion("CSS", "Easy",
                                    "What is the box model?"),

                            new InterviewQuestion("CSS", "Medium",
                                    "Difference between relative and absolute positioning?"),

                            new InterviewQuestion("CSS", "Hard",
                                    "Explain Flexbox and Grid.")
                    ),"Spring MVC", List.of(
                            new InterviewQuestion("Spring MVC", "Easy",
                                    "What is Spring MVC?"),

                            new InterviewQuestion("Spring MVC", "Medium",
                                    "Explain DispatcherServlet."),

                            new InterviewQuestion("Spring MVC", "Hard",
                                    "How does request mapping work?")
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
    public List<InterviewQuestion> generateQuestionsFromSkills(
            List<String> skills) {

        return generateQuestions(skills);
    }
}