package mls_data_classification_engine.mls_engine.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import mls_data_classification_engine.mls_engine.model.ClassificationLevel;
import mls_data_classification_engine.mls_engine.model.ClassifiedDocument;
import java.util.List;

public interface ClassifiedDocumentRepository extends JpaRepository<ClassifiedDocument, UUID> {

    // list instead of optional to return the list of classified documents
    List<ClassifiedDocument> findByClassificationOfDocument(ClassificationLevel classification);
}
