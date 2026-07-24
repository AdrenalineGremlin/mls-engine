package mls_data_classification_engine.mls_engine.model;

import java.util.UUID;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ClassifiedDocument {
    /*
     * content
     * title
     * document id
     * classification level
     * timstamp
     */

    @Id
    @GeneratedValue
    private UUID documentId;

    @Enumerated(EnumType.STRING)
    private ClassificationLevel classificationOfDocument;
    private String title;
    private String content;

    private Instant createdAt;
}
