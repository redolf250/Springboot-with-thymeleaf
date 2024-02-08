package com.redolf.thymeleaf.controller;

import com.redolf.thymeleaf.model.Document;
import com.redolf.thymeleaf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping("/uploadFile")
    private String saveDocument(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        service.savedDocument(multipartFile);
        return "Document saved!";
    }

    @GetMapping("/get/{documentId}")
    private ResponseEntity<?> downloadDocument(@PathVariable("documentId") Long documentId){
        Document document = service.getFileById(documentId).get();
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+document.getDocumentName()+"\"")
                    .body(new ByteArrayResource(document.getData()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
