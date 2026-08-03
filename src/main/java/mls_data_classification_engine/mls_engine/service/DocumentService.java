package mls_data_classification_engine.mls_engine.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import mls_data_classification_engine.mls_engine.repository.ClassifiedDocumentRepository;
import mls_data_classification_engine.mls_engine.repository.UserRepository;
import mls_data_classification_engine.mls_engine.security.AccessDecisionService;
import mls_data_classification_engine.mls_engine.model.AccessType;
import mls_data_classification_engine.mls_engine.model.ClassificationLevel;
import mls_data_classification_engine.mls_engine.model.ClassifiedDocument;
import mls_data_classification_engine.mls_engine.model.User;

@Service
public class DocumentService {
    private final ClassifiedDocumentRepository classifiedDocumentRepository;
    private final AccessDecisionService accessDecisionService;

    public ClassifiedDocument readDocument(User user, UUID id) {
        // get docu by id
        ClassifiedDocument document = classifiedDocumentRepository.findById(id).orElseThrow();
        // check if user can access to read
        boolean access = accessDecisionService.hasAccess(user, document, AccessType.READ);
        // if so return document
        if (access) {
            return document;
        }
        throw new RuntimeException("Cannot Access Document to READ");
    }

    public ClassifiedDocument writeDocument(User user, ClassifiedDocument document) {
        // check if user can access to write
        boolean access = accessDecisionService.hasAccess(user, document, AccessType.WRITE);
        // if so, save document /// write meaning saving into storage
        if (access) {
            return classifiedDocumentRepository.save(document);
        }
        throw new RuntimeException("Cannot Access Document to WRITE");
    }

    public DocumentService(ClassifiedDocumentRepository classifiedDocumentRepository,
            AccessDecisionService accessDecisionService) {
        this.classifiedDocumentRepository = classifiedDocumentRepository;
        this.accessDecisionService = accessDecisionService;

    }
}
