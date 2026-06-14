package interview_prep_system.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient =
            WebClient.builder().build();

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    public String analyzeResume(
            String resumeText)
            throws Exception {

        String prompt = """
                Analyze the following resume.

                Provide:
                1. Strengths
                2. Weaknesses
                3. Career Recommendations

                Resume:
                """ + resumeText;

        String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": %s
                        }
                      ]
                    }
                  ]
                }
                """.formatted(
                objectMapper.writeValueAsString(
                        prompt));

        String response =
                webClient.post()
                        .uri(
                                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                                        + apiKey)
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .bodyValue(requestBody)
                        .retrieve()
                        .onStatus(
                                status -> status.isError(),
                                clientResponse -> clientResponse.bodyToMono(String.class)
                                        .flatMap(body -> {
                                            System.out.println("GEMINI ERROR:");
                                            System.out.println(body);

                                            return reactor.core.publisher.Mono.error(
                                                    new RuntimeException(body));
                                        })
                        )
                        .bodyToMono(String.class)
                        .block();

        JsonNode root =
                objectMapper.readTree(response);

        return root
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();
    }

    public String evaluateAnswer(
            String skill,
            String difficulty,
            String question,
            String answer)
            throws Exception {

        String prompt = """
        You are a senior technical interviewer.

        Evaluate the candidate's answer.

        Skill:
        %s

        Difficulty:
        %s

        Question:
        %s

        Candidate Answer:
        %s

        Return ONLY valid JSON.

        Do not return markdown.
        Do not return explanations outside JSON.
        Do not wrap JSON in ```json blocks.

        Expected format:

        {
          "score": 0,
          "feedback": "",
          "strengths": [
            ""
          ],
          "improvements": [
            ""
          ]
        }

        Rules:
        - score must be between 0 and 10
        - strengths must contain 2-5 items
        - improvements must contain 2-5 items
        - feedback should be concise (2-4 sentences)
        - JSON must be valid and parseable
        """
                .formatted(
                        skill,
                        difficulty,
                        question,
                        answer);

        String requestBody = """
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": %s
                    }
                  ]
                }
              ]
            }
            """
                .formatted(
                        objectMapper.writeValueAsString(
                                prompt));

        String response =
                webClient.post()
                        .uri(
                                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                                        + apiKey)
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .bodyValue(requestBody)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

        JsonNode root =
                objectMapper.readTree(response);

        return root
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();
    }
}