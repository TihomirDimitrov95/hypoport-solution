package com.example.duckcodingchallenge.services;

import com.example.duckcodingchallenge.models.Document;
import com.example.duckcodingchallenge.models.DocumentsInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentsServiceImpl implements DocumentsService {

    public static final String INVALID_SEARCH_CRITERIA = "Please provide a valid sort criteria (e.g. name, type, size or deleted)";

    @Override
    public DocumentsInfo getDocumentInfo() throws IOException {
        Set<Document> documents = getDocuments();
        return getDetailedInformation(documents);
    }

    @Override
    public List<Document> getDocumentsFilteredByCategory(String targetCategory) throws IOException {
        Set<Document> documents = getDocuments();
        return documents.stream().filter(e -> e.getCategories().contains(targetCategory)).collect(Collectors.toList());
    }

    @Override
    public List<Document> getDocumentsSorted(String sortBy) throws IOException {
        Set<Document> documents = getDocuments();
        switch(sortBy.toLowerCase()) {
            case "name":
                return documents.stream().sorted(Comparator.comparing(Document::getName)).collect(Collectors.toList());
            case "type":
                return documents.stream().sorted(Comparator.comparing(Document::getType)).collect(Collectors.toList());
            case "size":
                return documents.stream().sorted(Comparator.comparing(Document::getSize)).collect(Collectors.toList());
            case "deleted":
                return documents.stream().sorted(Comparator.comparing(Document::getDeleted)).collect(Collectors.toList());
            default:
                throw new IllegalArgumentException(INVALID_SEARCH_CRITERIA);
        }
    }

    private Set<Document> getDocuments() throws IOException {
        String url = "http://localhost:8081/v1/documents";

        HttpGet request = new HttpGet(url);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(EntityUtils.toString(entity), new TypeReference<>(){});
    }


    private DocumentsInfo getDetailedInformation(Set<Document> documents) {
        DocumentsInfo documentsInfo = new DocumentsInfo();
        documentsInfo.setTotalNumber(documents.size());
        long deletedCount = documents.stream().filter(Document::getDeleted).count();
        documentsInfo.setTotalDeleted(deletedCount);
        long totalSize = documents.stream().map(Document::getSize).reduce(0L, Long::sum);
        documentsInfo.setTotalSize(totalSize);
        documentsInfo.setAverageSize(totalSize/ (documents.size() * 1.0));
        return documentsInfo;
    }
}
