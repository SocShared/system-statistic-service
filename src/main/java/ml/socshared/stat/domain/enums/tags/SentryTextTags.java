package ml.socshared.stat.domain.enums.tags;

public enum SentryTextTags {

    EXTRACT_KEY_WORDS("type","extract_key_words"),
    SEARCH_TARGET_PHRASES("type", "search_target_phrases"),
    ADD_TARGET_PHRASES("type", "add_target_phrases"),
    GET_TARGET_PHRASES("type", "get_target_phrases");

    SentryTextTags(String t, String tag) {
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
