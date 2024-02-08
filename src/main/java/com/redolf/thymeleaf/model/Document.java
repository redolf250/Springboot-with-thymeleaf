package com.redolf.thymeleaf.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_document")
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documentName;
    private String contentType;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
}
