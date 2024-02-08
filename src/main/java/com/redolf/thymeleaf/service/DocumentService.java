package com.redolf.thymeleaf.service;

import com.redolf.thymeleaf.model.Document;
import com.redolf.thymeleaf.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentService {

    private DocumentRepository repository;

    public String savedDocument(MultipartFile file) throws IOException {
        Document document = Document.builder()
                .documentName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .data(file.getBytes())
                .build();
        repository.save(document);
        return  "";
    }

    public Optional<Document> getFileById(Long documentId){
        final Optional<Document> document = repository.findById(documentId);
        if(document.isPresent()){
            return document;
        }
        return Optional.of(new Document());
    }

}
