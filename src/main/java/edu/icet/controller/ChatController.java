package edu.icet.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.MessageRequest;
import edu.icet.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Value("${ai.api.url}")
    private String aiApiUrl;

    @Value("${ai.api.key}")
    private String apiKey;

    @PostMapping("/sendMessage")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fullUrl = aiApiUrl + "?key=" + apiKey;

            var aiRequest = """
            {
                "contents": [
                    { "parts": [{ "text": "%s" }] }
                ]
            }
            """.formatted(request.getMessage());

            var headers = new org.springframework.http.HttpHeaders();
            headers.set("Content-Type", "application/json");

            var entity = new org.springframework.http.HttpEntity<>(aiRequest, headers);
            var aiResponse = restTemplate.postForEntity(fullUrl, entity, String.class);

            String aiText = "Sorry, something went wrong.";
            if (aiResponse.getStatusCode().is2xxSuccessful()) {
                var body = aiResponse.getBody();
                assert body != null;
                aiText = parseAiResponse(body);
            }
            return ResponseEntity.ok(new MessageResponse(aiText));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body(new MessageResponse("An error occurred while processing your request."));
        }
    }

    private String parseAiResponse(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            JsonNode candidates = root.path("candidates");
            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode content = candidates.get(0).path("content");
                JsonNode parts = content.path("parts");
                if (parts.isArray() && parts.size() > 0) {
                    return parts.get(0).path("text").asText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sorry, I encountered an error parsing the response.";
    }
}