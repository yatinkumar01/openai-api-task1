package com.example.codeConverter.controller;

import com.example.codeConverter.DTO.ClientPayload;
import com.example.codeConverter.DTO.ResponsePayload;
import com.example.codeConverter.Service.OpenApiService;
import com.example.codeConverter.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/chat")
public class ConverterController {

    OpenApiService openApiService;

    @Autowired
    public void setOpenApiService(OpenApiService openApiService) {
        this.openApiService = openApiService;
    }

    @PostMapping("/convert")
    public ResponseEntity convertCode(@RequestBody ClientPayload clientPayload) throws AppException {
        if (ObjectUtils.isEmpty(clientPayload)) {
            return ResponseEntity.badRequest().build();
        }
        ResponsePayload responsePayload = openApiService.convertCode(clientPayload);
        return new ResponseEntity(responsePayload, HttpStatus.OK);
    }


    @PostMapping("/debug")
    public ResponseEntity debugCode(@RequestBody ClientPayload clientPayload) throws AppException {
        if (ObjectUtils.isEmpty(clientPayload)) {
            return ResponseEntity.badRequest().build();
        }
        ResponsePayload responsePayload = openApiService.debugCode(clientPayload);
        return new ResponseEntity(responsePayload, HttpStatus.OK);
    }

    @PostMapping("/qualityCheck")
    public ResponseEntity qualityCheckCode(@RequestBody ClientPayload clientPayload) throws AppException {
        if (ObjectUtils.isEmpty(clientPayload)) {
            return ResponseEntity.badRequest().build();
        }
        ResponsePayload responsePayload = openApiService.qualityCheckCode(clientPayload);
        return new ResponseEntity(responsePayload, HttpStatus.OK);
    }
}
