package com.example.codeConverter.DTO.openApi;

import lombok.Data;

import java.util.List;

@Data
public class Request {

    private String model;
    private List<Message> messages;
    private Integer temperature = 1;
    private Integer max_tokens = 250;


}
