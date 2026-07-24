package mls_data_classification_engine.mls_engine.model;

public enum ClassificationLevel {
    UNCLASSIFIED,
    CONFIDENTIAL,
    SECRET,
    TOP_SECRET;

    // these will determine wether to read
    public boolean isAtLeast(ClassificationLevel other) {
        return this.ordinal() >= other.ordinal();
    }

    // or write
    public boolean isAtMost(ClassificationLevel other) {
        return this.ordinal() <= other.ordinal();
    }
}
