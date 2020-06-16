package ml.socshared.stat.domain.enums;

public enum SentryServerName {
    AUTH("server_name", "auth"),
    STORAGE("server_name", "storage"),
    VK_ADAPTER("server_name", "vk-adapter"),
    FB_ADAPTER("server_name", "fb-adapter"),
    BSTAT("server_name", "bstat"),
    MAIL_SENDER("server_name", "mail-sender"),
    GATEWAY("server_name", "gateway"),
    TECH_SUPPORT("server_name", "tech-support"),
    TEXT_ANALYZER("server_name", "text-analyzer"),
    WORKER("server_name", "worker");

    SentryServerName(String t, String tag) {
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
