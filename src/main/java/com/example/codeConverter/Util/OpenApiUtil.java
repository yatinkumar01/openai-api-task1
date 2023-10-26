package com.example.codeConverter.Util;

import com.example.codeConverter.DTO.ClientPayload;
import com.example.codeConverter.DTO.openApi.Message;
import com.example.codeConverter.DTO.openApi.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenApiUtil {

    @Value("${openai.model}")
    private String model;

    public Request getConvertRequest(ClientPayload clientPayload) {
        Message systemMessage = new Message("system", "Act as a code convert which converts code in any language");
        Message userMessage = new Message("user", clientPayload.getPrompt() + " Convert this code to " + clientPayload.getLanguage());

        List<Message> messageList = new ArrayList<>();
        messageList.add(systemMessage);
        messageList.add(userMessage);

        Request request = new Request();
        request.setModel(model);
        request.setMessages(messageList);
        return request;
    }

    public Request getDebugRequest(ClientPayload clientPayload) {
        Message systemMessage = new Message("system", "Help me to solve the issue and write updated code. Also explain what the issue was and what you did to fix it");
        Message userMessage = new Message("user", clientPayload.getPrompt());

        List<Message> messageList = new ArrayList<>();
        messageList.add(systemMessage);
        messageList.add(userMessage);

        Request request = new Request();
        request.setModel(model);
        request.setMessages(messageList);
        return request;
    }

    public Request getQualityCheckRequest(ClientPayload clientPayload) {
        Message systemMessage = new Message("system", "Please evaluate the code quality based on the following criteria:\n" +
                "\n" +
                "1. Code Consistency: Assess the code for consistency in coding style, naming conventions, and formatting.\n" +
                "2. Code Performance: Evaluate the code for efficiency in algorithms, optimized data structures, and overall performance considerations.\n" +
                "3. Code Documentation: Review the code for appropriate comments, inline documentation, and clear explanations of complex logic.\n" +
                "4. Error Handling: Examine the code for proper error handling and graceful error recovery mechanisms.\n" +
                "5. Code Testability: Evaluate the code for ease of unit testing, mocking, and overall testability.\n" +
                "6. Code Modularity: Assess the code for a modular design, separation of concerns, and reusability of components.\n" +
                "7. Code Complexity: Analyze the code for excessive complexity, convoluted logic, and potential code smells.\n" +
                "8. Code Duplication: Identify any code duplication and assess its impact on maintainability and readability.\n" +
                "9. Code Readability: Evaluate the code for readability, clarity, and adherence to coding best practices.\n" +
                "\n" +
                "Please provide a summary of the code quality assessment and a report showing the percentage-wise evaluation for each parameter mentioned above and give answer in proper points.");

        Message userMessage = new Message("user", clientPayload.getPrompt());

        List<Message> messageList = new ArrayList<>();
        messageList.add(systemMessage);
        messageList.add(userMessage);

        Request request = new Request();
        request.setModel(model);
        request.setMessages(messageList);
        return request;
    }
}
