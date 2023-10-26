package com.example.codeConverter.Service;

import com.example.codeConverter.DTO.ClientPayload;
import com.example.codeConverter.DTO.ResponsePayload;
import com.example.codeConverter.DTO.openApi.Request;
import com.example.codeConverter.DTO.openApi.Response;
import com.example.codeConverter.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.codeConverter.Util.OpenApiUtil;

@Service
public class OpenApiService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")  // Fixed the typo in Value annotation
    private String apiURL;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final OpenApiUtil openApiUtil;


    public OpenApiService(RestTemplate restTemplate, OpenApiUtil openApiUtil) {
        this.restTemplate = restTemplate;
        this.openApiUtil = openApiUtil;
    }

    public ResponsePayload convertCode(ClientPayload clientPayload) throws AppException {
        try {
            Request request = openApiUtil.getConvertRequest(clientPayload);
            Response response = restTemplate.postForObject(apiURL, request, Response.class);
            ResponsePayload responsePayload = new ResponsePayload(response.getChoices().get(0).getMessage().getContent());
            return responsePayload;
        } catch (Exception exception) {
            throw new AppException("Sorry Something Went Wrong");
        }
    }

    public ResponsePayload debugCode(ClientPayload clientPayload) throws AppException {
        try {
            Request request = openApiUtil.getDebugRequest(clientPayload);
            Response response = restTemplate.postForObject(apiURL, request, Response.class);
            ResponsePayload responsePayload = new ResponsePayload(response.getChoices().get(0).getMessage().getContent());
            return responsePayload;
        } catch (Exception exception) {
            throw new AppException("Sorry Something Went Wrong");
        }
    }

    public ResponsePayload qualityCheckCode(ClientPayload clientPayload) throws AppException {
        try {
            Request request = openApiUtil.getQualityCheckRequest(clientPayload);
            Response response = restTemplate.postForObject(apiURL, request, Response.class);
            ResponsePayload responsePayload = new ResponsePayload(response.getChoices().get(0).getMessage().getContent());
            return responsePayload;
        } catch (Exception exception) {
            throw new AppException("Sorry Something Went Wrong");
        }
    }
}
