package mls_data_classification_engine.mls_engine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mls_data_classification_engine.mls_engine.model.ClassifiedDocument;
import mls_data_classification_engine.mls_engine.model.User;
import mls_data_classification_engine.mls_engine.service.DocumentService;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// controller gets http request, extracts data from request
// hand data to service and package anything that comes back,
// into http response
@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    // getmapping retrieves
    @GetMapping("/{id}")
    public ClassifiedDocument readDocument(@AuthenticationPrincipal User user, @PathVariable UUID id) {
        return documentService.readDocument(user, id);
    }

    // postmapping for creating
    @PostMapping("/write")
    public ClassifiedDocument writeDocument(@AuthenticationPrincipal User user,
            @RequestBody ClassifiedDocument document) {
        return documentService.writeDocument(user, document);
    }

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
}
