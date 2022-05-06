package com.example.duckcodingchallenge.services;

import com.example.duckcodingchallenge.models.Document;
import com.example.duckcodingchallenge.models.DocumentsInfo;

import java.io.IOException;
import java.util.List;

public interface DocumentsService {
    DocumentsInfo getDocumentInfo() throws IOException;

    List<Document> getDocumentsFilteredByCategory(String targetCategory) throws IOException;

    List<Document> getDocumentsSorted(String sortBy) throws IOException;
}
