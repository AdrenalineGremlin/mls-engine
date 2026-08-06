package mls_data_classification_engine.mls_engine.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import mls_data_classification_engine.mls_engine.security.AccessDecisionService;
import mls_data_classification_engine.mls_engine.model.User;
import mls_data_classification_engine.mls_engine.model.ClassifiedDocument;
import mls_data_classification_engine.mls_engine.model.ClassificationLevel;
import mls_data_classification_engine.mls_engine.model.AccessType;

public class AccessDecisionServiceTest {

    User user = new User(null, ClassificationLevel.SECRET, null, null);
    ClassifiedDocument classifiedDocument = new ClassifiedDocument(null, ClassificationLevel.SECRET, null, null, null);
    User user1 = new User(null, ClassificationLevel.TOP_SECRET, null, null);
    ClassifiedDocument classifiedDocument1 = new ClassifiedDocument(null, ClassificationLevel.SECRET, null, null,
            null);
    User user2 = new User(null, ClassificationLevel.UNCLASSIFIED, null, null);
    ClassifiedDocument classifiedDocument2 = new ClassifiedDocument(null, ClassificationLevel.SECRET, null, null,
            null);
    AccessDecisionService accessDecisionService = new AccessDecisionService();

    @Test
    public void eqaulClearanceCanRead() {

        boolean access = accessDecisionService.hasAccess(user, classifiedDocument, AccessType.READ);
        assertTrue(access);
    }

    @Test
    public void higherClearanceCanRead() {

        boolean access = accessDecisionService.hasAccess(user1, classifiedDocument1, AccessType.READ);
        assertTrue(access);
    }

    @Test
    public void LowerClearanceCantRead() {

        boolean access = accessDecisionService.hasAccess(user2, classifiedDocument2, AccessType.READ);
        assertFalse(access);
    }

    @Test
    public void eqaulClearanceCanWrite() {

        boolean access = accessDecisionService.hasAccess(user, classifiedDocument, AccessType.WRITE);
        assertTrue(access);
    }

    @Test
    public void higherClearanceCantWrite() {

        boolean access = accessDecisionService.hasAccess(user1, classifiedDocument1, AccessType.WRITE);
        assertFalse(access);
    }

    @Test
    public void lowerClearanceCanWrite() {

        boolean access = accessDecisionService.hasAccess(user2, classifiedDocument2, AccessType.WRITE);
        assertTrue(access);
    }

}
