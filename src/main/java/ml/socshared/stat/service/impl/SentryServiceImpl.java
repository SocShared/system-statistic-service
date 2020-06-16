package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ml.socshared.stat.client.SentryFeignClient;
import ml.socshared.stat.domain.enums.SentryServerName;
import ml.socshared.stat.domain.enums.tags.SentryFacebookTags;
import ml.socshared.stat.domain.enums.tags.SentryVkTags;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.UsingSocialNetworkResponse;
import ml.socshared.stat.domain.response.usingsocial.FacebookEventsResponse;
import ml.socshared.stat.domain.response.usingsocial.VkEventsResponse;
import ml.socshared.stat.service.SentryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SentryServiceImpl implements SentryService {

    private final SentryFeignClient client;

    @Value("${sentry.api.key}")
    private String token;

    private String token() {
        return "Bearer " + token;
    }

    @Override
    public SentryIssueResponse[] getIssues(String query) {
        return client.getIssues(query, token());
    }

    @Override
    public UsingSocialNetworkResponse getUsingSocialNetwork() {
        VkEventsResponse vkEventsResponse = findVkEvents();

        FacebookEventsResponse facebookEventsResponse = findFacebookEvents();

        return UsingSocialNetworkResponse.builder()
                .facebook(facebookEventsResponse)
                .vk(vkEventsResponse)
                .build();
    }

    private VkEventsResponse findVkEvents() {
        VkEventsResponse vkEventsResponse = new VkEventsResponse();

        SentryIssueResponse[] vkUserInfo = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_USER_INFO, token());
        if (vkUserInfo.length == 1)
            vkEventsResponse.setUserInfoEventsCount(Long.parseLong(vkUserInfo[0].getCount()));

        SentryIssueResponse[] vkCommentsOfPost = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.COMMENTS_OF_POST, token());
        if (vkCommentsOfPost.length == 1)
            vkEventsResponse.setCommentsOfPostEventsCount(Long.parseLong(vkCommentsOfPost[0].getCount()));

        SentryIssueResponse[] vkCommentOfPost = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.COMMENT_OF_POST, token());
        if (vkCommentOfPost.length == 1)
            vkEventsResponse.setCommentOfPostEventsCount(Long.parseLong(vkCommentOfPost[0].getCount()));

        SentryIssueResponse[] vkSubComments = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_SUB_COMMENTS, token());
        if (vkSubComments.length == 1)
            vkEventsResponse.setSubCommentsEventsCount(Long.parseLong(vkSubComments[0].getCount()));

        SentryIssueResponse[] vkSumCommentById = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_SUB_COMMENT_BY_ID, token());
        if (vkSumCommentById.length == 1)
            vkEventsResponse.setSubCommentByIdEventsCount(Long.parseLong(vkSumCommentById[0].getCount()));

        SentryIssueResponse[] vkUserGroups = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_USER_GROUPS, token());
        if (vkUserGroups.length == 1)
            vkEventsResponse.setUserGroupsEventsCount(Long.parseLong(vkUserGroups[0].getCount()));

        SentryIssueResponse[] vkUserGroup = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_USER_GROUP, token());
        if (vkUserGroup.length == 1)
            vkEventsResponse.setUserGroupEventsCount(Long.parseLong(vkUserGroup[0].getCount()));

        SentryIssueResponse[] vkPosts = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_POSTS, token());
        if (vkPosts.length == 1)
            vkEventsResponse.setPostsEventsCount(Long.parseLong(vkPosts[0].getCount()));

        SentryIssueResponse[] vkPost = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.GET_POST, token());
        if (vkPost.length == 1)
            vkEventsResponse.setPostByIdEventsCount(Long.parseLong(vkPost[0].getCount()));

        SentryIssueResponse[] vkAddPost = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.ADD_POST, token());
        if (vkAddPost.length == 1)
            vkEventsResponse.setAddPostEventsCount(Long.parseLong(vkAddPost[0].getCount()));

        SentryIssueResponse[] vkUpdatePost = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.UPDATE_POST, token());
        if (vkUpdatePost.length == 1)
            vkEventsResponse.setUpdatePostEventsCount(Long.parseLong(vkUpdatePost[0].getCount()));

        SentryIssueResponse[] vkDeletePost = client.getIssues("server_name:"+SentryServerName.VK_ADAPTER+" type:" + SentryVkTags.DELETE_POST, token());
        if (vkDeletePost.length == 1)
            vkEventsResponse.setDeletePostEventsCount(Long.parseLong(vkDeletePost[0].getCount()));

        long allEventsCount = vkEventsResponse.getUserInfoEventsCount() + vkEventsResponse.getCommentsOfPostEventsCount() +
                vkEventsResponse.getCommentOfPostEventsCount() + vkEventsResponse.getSubCommentsEventsCount() +
                vkEventsResponse.getSubCommentByIdEventsCount() + vkEventsResponse.getUserGroupsEventsCount() +
                vkEventsResponse.getUserGroupEventsCount() + vkEventsResponse.getPostsEventsCount() +
                vkEventsResponse.getPostByIdEventsCount() + vkEventsResponse.getAddPostEventsCount() +
                vkEventsResponse.getUpdatePostEventsCount() + vkEventsResponse.getDeletePostEventsCount();

        vkEventsResponse.setAllEventsCount(allEventsCount);

        return vkEventsResponse;
    }

    private FacebookEventsResponse findFacebookEvents() {
        FacebookEventsResponse facebookEventsResponse = new FacebookEventsResponse();

        SentryIssueResponse[] fbAllFacebookAccount = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_ALL_FACEBOOK_ACCOUNT, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_ALL_FACEBOOK_ACCOUNT);
        if (fbAllFacebookAccount.length == 1)
            facebookEventsResponse.setAllFacebookAccountEventsCount(Long.parseLong(fbAllFacebookAccount[0].getCount()));

        SentryIssueResponse[] fbSaveAccount = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.SAVE_ACCOUNT, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.SAVE_ACCOUNT);
        if (fbSaveAccount.length == 1)
            facebookEventsResponse.setSaveAccountEventsCount(Long.parseLong(fbSaveAccount[0].getCount()));

        SentryIssueResponse[] fbRemoveAccount = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.REMOVE_ACCOUNT, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.REMOVE_ACCOUNT);
        if (fbRemoveAccount.length == 1)
            facebookEventsResponse.setRemoveAccountEventsCount(Long.parseLong(fbRemoveAccount[0].getCount()));

        SentryIssueResponse[] fbUrlForming = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.URL_FORMING, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.URL_FORMING);
        if (fbUrlForming.length == 1)
            facebookEventsResponse.setUrlFormingEventsCount(Long.parseLong(fbUrlForming[0].getCount()));

        SentryIssueResponse[] fbUserInfo = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_USER_INFO, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_USER_INFO);
        if (fbUserInfo.length == 1)
            facebookEventsResponse.setUserInfoEventsCount(Long.parseLong(fbUserInfo[0].getCount()));

        SentryIssueResponse[] fbCommentsOfPost = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.COMMENTS_OF_POST, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.COMMENTS_OF_POST);
        if (fbCommentsOfPost.length == 1)
            facebookEventsResponse.setCommentsOfPostEventsCount(Long.parseLong(fbCommentsOfPost[0].getCount()));

        SentryIssueResponse[] fbCommentOfPost = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.COMMENT_OF_POST, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.COMMENT_OF_POST);
        if (fbCommentOfPost.length == 1)
            facebookEventsResponse.setCommentOfPostEventsCount(Long.parseLong(fbCommentOfPost[0].getCount()));

        SentryIssueResponse[] fbSubComments = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_SUB_COMMENTS, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_SUB_COMMENTS);
        if (fbSubComments.length == 1)
            facebookEventsResponse.setSubCommentsEventsCount(Long.parseLong(fbSubComments[0].getCount()));

        SentryIssueResponse[] fbSumCommentById = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_SUB_COMMENT_BY_ID, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_SUB_COMMENT_BY_ID);
        if (fbSumCommentById.length == 1)
            facebookEventsResponse.setSubCommentByIdEventsCount(Long.parseLong(fbSumCommentById[0].getCount()));

        SentryIssueResponse[] fbUserGroups = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_USER_GROUPS, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_USER_GROUPS);
        if (fbUserGroups.length == 1)
            facebookEventsResponse.setUserGroupsEventsCount(Long.parseLong(fbUserGroups[0].getCount()));

        SentryIssueResponse[] fbUserGroup = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_USER_GROUP, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_USER_GROUP);
        if (fbUserGroup.length == 1)
            facebookEventsResponse.setUserGroupEventsCount(Long.parseLong(fbUserGroup[0].getCount()));

        SentryIssueResponse[] fbPosts = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_POSTS, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_POSTS);
        if (fbPosts.length == 1)
            facebookEventsResponse.setPostsEventsCount(Long.parseLong(fbPosts[0].getCount()));

        SentryIssueResponse[] fbPost = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_POST, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.GET_POST);
        if (fbPost.length == 1)
            facebookEventsResponse.setPostByIdEventsCount(Long.parseLong(fbPost[0].getCount()));

        SentryIssueResponse[] fbAddPost = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.ADD_POST, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.ADD_POST);
        if (fbAddPost.length == 1)
            facebookEventsResponse.setAddPostEventsCount(Long.parseLong(fbAddPost[0].getCount()));

        SentryIssueResponse[] fbUpdatePost = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.UPDATE_POST, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.UPDATE_POST);
        if (fbUpdatePost.length == 1)
            facebookEventsResponse.setUpdatePostEventsCount(Long.parseLong(fbUpdatePost[0].getCount()));

        SentryIssueResponse[] fbDeletePost = client.getIssues("server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.DELETE_POST, token());
        log.info("query: server_name:"+SentryServerName.FB_ADAPTER+" type:" + SentryFacebookTags.DELETE_POST);
        if (fbDeletePost.length == 1)
            facebookEventsResponse.setDeletePostEventsCount(Long.parseLong(fbDeletePost[0].getCount()));

        long allEventsCount = facebookEventsResponse.getAllFacebookAccountEventsCount() +
                facebookEventsResponse.getSaveAccountEventsCount() +
                facebookEventsResponse.getRemoveAccountEventsCount() +
                facebookEventsResponse.getUrlFormingEventsCount() +
                facebookEventsResponse.getUserInfoEventsCount() +
                facebookEventsResponse.getCommentsOfPostEventsCount() +
                facebookEventsResponse.getCommentOfPostEventsCount() +
                facebookEventsResponse.getSubCommentsEventsCount() +
                facebookEventsResponse.getSubCommentByIdEventsCount() +
                facebookEventsResponse.getUserGroupsEventsCount() +
                facebookEventsResponse.getUserGroupEventsCount() +
                facebookEventsResponse.getPostsEventsCount() +
                facebookEventsResponse.getPostByIdEventsCount() +
                facebookEventsResponse.getAddPostEventsCount() +
                facebookEventsResponse.getUpdatePostEventsCount() +
                facebookEventsResponse.getDeletePostEventsCount();

        facebookEventsResponse.setAllEventsCount(allEventsCount);

        return facebookEventsResponse;
    }
}
