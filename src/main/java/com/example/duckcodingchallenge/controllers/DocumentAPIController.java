package com.example.duckcodingchallenge.controllers;

import com.example.duckcodingchallenge.models.Document;
import com.example.duckcodingchallenge.models.DocumentsInfo;
import com.example.duckcodingchallenge.services.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/documents")
public class DocumentAPIController {

    public static final String ERROR_MESSAGE_DOCUMENTS_NOT_AVAILABLE = "There was an error with accessing the documents API we rely on. Please try again later.";
    private final DocumentsService service;

    @Autowired
    public DocumentAPIController(DocumentsService service) {
        this.service = service;
    }

    @GetMapping("/details")
    public DocumentsInfo getDocumentDetails() {
        try {
            return service.getDocumentInfo();
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ERROR_MESSAGE_DOCUMENTS_NOT_AVAILABLE);
        }
    }

    @GetMapping("/filter")
    public List<Document> getDocumentsFiltered(@RequestParam(name = "category") String category) {
        try {
            return service.getDocumentsFilteredByCategory(category);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ERROR_MESSAGE_DOCUMENTS_NOT_AVAILABLE);
        }
    }

    @GetMapping("/sorted")
    public List<Document> getDocumentsSorted(@RequestParam(name = "sortBy") String sortBy) {
        try {
            return service.getDocumentsSorted(sortBy);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ERROR_MESSAGE_DOCUMENTS_NOT_AVAILABLE);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
