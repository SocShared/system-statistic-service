package ml.socshared.stat.domain;

public enum SentryTags {

    // Auth
    REGISTRATION_USER("type", "registration_user"),
    EDIT_USER_DATA("type", "edit_user_data"),
    UPDATE_USER_PASSWORD("type", "update_user_password"),
    DELETE_USER_BY_ID("type", "delete_user_by_id"),
    GET_USER_BY_ID("type", "get_user_by_id"),
    GET_USER_BY_USERNAME("type", "get_user_by_username"),
    GET_USER_BY_EMAIL("type", "get_user_by_email"),
    GET_USERS("type", "get_users"),
    CHECK_USERNAME_AND_PASSWORD("type", "check_username_and_password"),
    ADD_ROLE_FOR_USER("type", "add_role_for_user"),
    REMOVE_ROLE_FOR_USER("type", "remove_role_for_user"),
    RESET_PASSWORD("type", "reset_password"),
    GET_SERVICE_TOKEN("type", "get_service_token_response"),
    GET_TOKEN_BY_USERNAME_AND_PASSWORD("type", "get_token_by_username_and_password"),
    GET_TOKEN_BY_REFRESH_TOKEN("type", "get_token_by_refresh_token"),
    GET_TOKEN_BY_AUTHORIZATION_CODE("type", "get_token_by_authorization_code"),
    GET_TOKEN_BY_CLIENT_CREDENTIALS("type", "get_token_by_client_credentials"),
    ADD_CLIENT("type", "add_client"),
    UPDATE_CLIENT("type", "update_client"),
    GET_CLIENT_BY_USER("type", "get_client_by_user"),
    METRICS_USERS("type", "metrics_users"),
    // Text Analyzer
    EXTRACT_KEY_WORDS("type","extract_key_words"),
    SEARCH_TARGET_PHRASES("type", "search_target_phrases"),
    ADD_TARGET_PHRASES("type", "add_target_phrases"),
    GET_TARGET_PHRASES("type", "get_target_phrases"),
    // Storage
    SAVE_GROUP("type", "save_group"),
    DELETE_GROUP_BY_ID("type", "delete_group_by_id"),
    DELETE_GROUP_BY_VK_ID("type", "delete_group_by_vk_group_id"),
    DELETE_GROUP_BY_FB_ID("type", "delete_group_by_fb_group_id"),
    GET_GROUP_BY_ID("type", "get_group_by_id"),
    GET_GROUPS_BY_USER_ID("type", "get_groups_by_user_id"),
    GET_GROUPS_BY_USER_ID_AND_SOCIAL_NETWORK("type", "get_groups_by_user_id_and_social_network"),
    GET_BY_USER_ID_AND_VK_ID("type", "get_by_user_id_and_vk_id"),
    GET_BY_USER_ID_AND_FACEBOOK_ID("type", "get_by_user_id_and_facebook_id"),
    DELETE_VK_GROUPS_BY_USER_ID("type", "delete_vk_groups_by_user_id"),
    DELETE_FACEBOOK_GROUPS_BY_USER_ID("type", "delete_facebook_groups_by_user_id"),
    SAVE_PUBLICATION("type", "save_publication"),
    GET_NOT_PUBLISHING_PUBLICATIONS("type", "get_not_publishing_publications"),
    GET_PUBLISHING_AFTER_PUBLICATIONS("type", "get_publishing_after_publications"),
    GET_PUBLICATIONS_BY_GROUP_ID("type", "get_publications_by_group_id"),
    // Mail Sender
    SEND_MAIL("type", "send_mail"),
    SEND_MAIL_CONFIRM("type", "send_mail_confirm"),
    SEND_MAIL_RESET_PASSWORD("type",  "send_mail_reset_password"),
    // Support
    CREATE_QUESTION("type", "create_question"),
    GET_QUESTIONS("type", "get_questions_page"),
    GET_FULL_QUESTION("type", "get_full_question"),
    ADD_COMMENT("type", "add_comment"),
    REMOVE_QUESTION("type", "remove_question"),
    REMOVE_COMMENT("type", "remove_comment"),
    // BSTAT
    POST_SUMMARY("type", "post_summary"),
    GROUP_UPDATE("type", "group_update"),
    POST_UPDATE("type", "post_update"),
    POST_INFO("type", "get_post_info"),
    GROUP_ONLINE("type", "get_group_online"),
    SCHEDULED_STATISTIC_COLLECTION("type", "scheduled_statistic_collection");

    SentryTags(String t, String tag) {
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
