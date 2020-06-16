package ml.socshared.stat.domain.enums.tags;

public enum SentrySupportTags {
    CREATE_QUESTION("type", "create_question"),
    GET_QUESTIONS("type", "get_questions_page"),
    GET_FULL_QUESTION("type", "get_full_question"),
    ADD_COMMENT("type", "add_comment"),
    REMOVE_QUESTION("type", "remove_question"),
    REMOVE_COMMENT("type", "remove_comment");

    SentrySupportTags(String t, String tag) {
        type = t;
        sentryTag = tag;
    }

    public String type() {
        return type;
    }
    public String value() {
        return sentryTag;
    }

    private final String sentryTag;
    private final String type;
}
