package mls_data_classification_engine.mls_engine.security;

import mls_data_classification_engine.mls_engine.model.AccessType;
import mls_data_classification_engine.mls_engine.model.ClassifiedDocument;
import mls_data_classification_engine.mls_engine.model.User;

public class AccessDecisionService {
    public boolean hasAccess(User user, ClassifiedDocument document, AccessType access) {

        // the action of acess gets the accesstype, if its read
        // system gets user clearancelevel and compares if its atleast up to document
        // classfication
        if (access == AccessType.READ) {
            return user.getClearanceLevel().isAtLeast(document.getClassificationOfDocument());

        }
        // the action of acess gets the accesstype, if its read
        // system gets user clearancelevel and compares if its atleast up to document
        // classfication
        if (access == AccessType.WRITE) {
            return user.getClearanceLevel().isAtMost(document.getClassificationOfDocument());
        }

        // return false as a security fail safe
        return false;

    }
}
