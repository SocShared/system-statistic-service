package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ml.socshared.stat.client.SentryFeignClient;
import ml.socshared.stat.domain.enums.SentryServerName;
import ml.socshared.stat.domain.enums.tags.SentryAuthTags;
import ml.socshared.stat.domain.enums.tags.SentryFacebookTags;
import ml.socshared.stat.domain.enums.tags.SentryVkTags;
import ml.socshared.stat.domain.response.SentryEventResponse;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.usingsocial.UsingSocialNetworkResponse;
import ml.socshared.stat.domain.response.errorstat.ErrorsStatResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.domain.response.usingsocial.FacebookEventsResponse;
import ml.socshared.stat.domain.response.usingsocial.VkEventsResponse;
import ml.socshared.stat.exception.impl.HttpBadGatewayException;
import ml.socshared.stat.service.SentryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
        return usingSocialNetworkResponse;
    }

    @Override
    public ErrorsStatResponse getErrorsStat() {
        return errorsStatResponse;
    }

    @Override
    public List<UsersStatResponse> getOnlineUsersStatTimeline() {
        return onlineStatResponse;
    }

    @Override
    public List<UsersStatResponse> getActiveUsersStatTimeline() {
        return activeStatResponse;
    }

    @Override
    public List<UsersStatResponse> getNewUsersStatTimeline() {
        return newStatResponse;
    }

    @Override
    public List<UsersStatResponse> getAllUsersStatTimeline() {
        return allStatResponse;
    }

    public List<UsersStatResponse> getOnlineUsersStatTimelineSentry() {
        SentryIssueResponse[] issueResponse = client.getIssues("level:info server_name:" + SentryServerName.AUTH.value() +
                " type:" + SentryAuthTags.ONLINE_USERS.value(), token());

        List<UsersStatResponse> lists = new LinkedList<>();
        if (issueResponse.length == 1) {
            SentryEventResponse[] events = client.getEvents(issueResponse[0].getId(),
                    "level:info server_name:" + SentryServerName.AUTH.value() + " type:" + SentryAuthTags.ONLINE_USERS.value(),
                    token());

            for (SentryEventResponse event : events) {

                Map<String, Integer> context = (HashMap) event.getContext();

                UsersStatResponse response = UsersStatResponse.builder()
                        .onlineUsers(context.get("online_users").longValue())
                        .dateTime(event.getDateReceived())
                        .build();

                lists.add(response);
                if (lists.size() == 30)
                    break;
            }

        }

        if (lists.size() == 0) {
            UsersStatResponse usersStatResponse = UsersStatResponse.builder()
                    .onlineUsers(0L)
                    .dateTime(LocalDateTime.now())
                    .build();
            lists.add(usersStatResponse);
        }

        while (lists.size() < 30) {
            lists.add(UsersStatResponse.builder()
                    .onlineUsers(0L)
                    .dateTime(lists.get(lists.size() - 1).getDateTime().minusDays(1))
                    .build());
        }

        return lists;
    }

    public List<UsersStatResponse> getActiveUsersStatTimelineSentry() {
        SentryIssueResponse[] issueResponse = client.getIssues("level:info server_name:" + SentryServerName.AUTH.value() +
                " type:" + SentryAuthTags.ACTIVE_USERS.value(), token());

        List<UsersStatResponse> lists = new LinkedList<>();
        if (issueResponse.length == 1) {
            SentryEventResponse[] events = client.getEvents(issueResponse[0].getId(),
                    "level:info server_name:" + SentryServerName.AUTH.value() + " type:" + SentryAuthTags.ACTIVE_USERS.value(),
                    token());

            for (SentryEventResponse event : events) {

                Map<String, Integer> context = (HashMap) event.getContext();

                UsersStatResponse response = UsersStatResponse.builder()
                        .activeUsers(context.get("active_users").longValue())
                        .dateTime(event.getDateReceived())
                        .build();

                lists.add(response);
                if (lists.size() == 30)
                    break;
            }

        }

        if (lists.size() == 0) {
            UsersStatResponse usersStatResponse = UsersStatResponse.builder()
                    .onlineUsers(0L)
                    .dateTime(LocalDateTime.now())
                    .build();
            lists.add(usersStatResponse);
        }

        while (lists.size() < 30) {
            lists.add(UsersStatResponse.builder()
                    .activeUsers(0L)
                    .dateTime(lists.get(lists.size() - 1).getDateTime().minusDays(1))
                    .build());
        }

        return lists;
    }

    public List<UsersStatResponse> getNewUsersStatTimelineSentry() {
        SentryIssueResponse[] issueResponse = client.getIssues("level:info server_name:" + SentryServerName.AUTH.value() +
                " type:" + SentryAuthTags.NEW_USERS.value(), token());

        List<UsersStatResponse> lists = new LinkedList<>();
        if (issueResponse.length == 1) {
            SentryEventResponse[] events = client.getEvents(issueResponse[0].getId(),
                    "level:info server_name:" + SentryServerName.AUTH.value() + " type:" + SentryAuthTags.NEW_USERS.value(),
                    token());

            for (SentryEventResponse event : events) {

                Map<String, Integer> context = (HashMap) event.getContext();

                UsersStatResponse response = UsersStatResponse.builder()
                        .newUsers(context.get("new_users").longValue())
                        .dateTime(event.getDateReceived())
                        .build();

                lists.add(response);
                if (lists.size() == 30)
                    break;
            }

        }

        if (lists.size() == 0) {
            UsersStatResponse usersStatResponse = UsersStatResponse.builder()
                    .onlineUsers(0L)
                    .dateTime(LocalDateTime.now())
                    .build();
            lists.add(usersStatResponse);
        }

        while (lists.size() < 30) {
            lists.add(UsersStatResponse.builder()
                    .newUsers(0L)
                    .dateTime(lists.get(lists.size() - 1).getDateTime().minusDays(1))
                    .build());
        }

        return lists;
    }

    public List<UsersStatResponse> getAllUsersStatTimelineSentry() {
        SentryIssueResponse[] issueResponse = client.getIssues("level:info server_name:" + SentryServerName.AUTH.value() +
                " type:" + SentryAuthTags.ALL_USERS.value(), token());

        List<UsersStatResponse> lists = new LinkedList<>();
        if (issueResponse.length == 1) {
            SentryEventResponse[] events = client.getEvents(issueResponse[0].getId(),
                    "level:info server_name:" + SentryServerName.AUTH.value() + " type:" + SentryAuthTags.ALL_USERS.value(),
                    token());

            for (SentryEventResponse event : events) {

                Map<String, Integer> context = (HashMap) event.getContext();

                UsersStatResponse response = UsersStatResponse.builder()
                        .allUsers(context.get("all_users").longValue())
                        .dateTime(event.getDateReceived())
                        .build();

                lists.add(response);
                if (lists.size() == 30)
                    break;
            }

        }

        if (lists.size() == 0) {
            UsersStatResponse usersStatResponse = UsersStatResponse.builder()
                    .onlineUsers(0L)
                    .dateTime(LocalDateTime.now())
                    .build();
            lists.add(usersStatResponse);
        }

        while (lists.size() < 30) {
            lists.add(UsersStatResponse.builder()
                    .allUsers(0L)
                    .dateTime(lists.get(lists.size() - 1).getDateTime().minusDays(1))
                    .build());
        }

        return lists;
    }

    private long countErrors(SentryIssueResponse[] errors) {
        long count = 0;
        for (SentryIssueResponse response : errors) {
            count += Long.parseLong(response.getCount());
        }
        return count;
    }

    public UsingSocialNetworkResponse getUsingSocialNetworkSentry() {
        VkEventsResponse vkEventsResponse = findVkEvents();

        FacebookEventsResponse facebookEventsResponse = findFacebookEvents();

        return UsingSocialNetworkResponse.builder()
                .facebook(facebookEventsResponse)
                .vk(vkEventsResponse)
                .build();
    }

    private VkEventsResponse findVkEvents() {
        VkEventsResponse vkEventsResponse = new VkEventsResponse();

        long allEventsCount = 0;
        SentryIssueResponse[] vkUserInfo = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_USER_INFO.value(), token());
        if (vkUserInfo.length == 1) {
            vkEventsResponse.setUserInfoEventsCount(Long.parseLong(vkUserInfo[0].getCount()));
            allEventsCount += vkEventsResponse.getUserInfoEventsCount();
        }

        SentryIssueResponse[] vkCommentsOfPost = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.COMMENTS_OF_POST.value(), token());
        if (vkCommentsOfPost.length == 1) {
            vkEventsResponse.setCommentsOfPostEventsCount(Long.parseLong(vkCommentsOfPost[0].getCount()));
            allEventsCount += vkEventsResponse.getCommentOfPostEventsCount();
        }

        SentryIssueResponse[] vkCommentOfPost = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.COMMENT_OF_POST.value(), token());
        if (vkCommentOfPost.length == 1) {
            vkEventsResponse.setCommentOfPostEventsCount(Long.parseLong(vkCommentOfPost[0].getCount()));
            allEventsCount += vkEventsResponse.getCommentOfPostEventsCount();
        }

        SentryIssueResponse[] vkSubComments = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_SUB_COMMENTS.value(), token());
        if (vkSubComments.length == 1) {
            vkEventsResponse.setSubCommentsEventsCount(Long.parseLong(vkSubComments[0].getCount()));
            allEventsCount += vkEventsResponse.getSubCommentsEventsCount();
        }

        SentryIssueResponse[] vkSumCommentById = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_SUB_COMMENT_BY_ID.value(), token());
        if (vkSumCommentById.length == 1) {
            vkEventsResponse.setSubCommentByIdEventsCount(Long.parseLong(vkSumCommentById[0].getCount()));
            allEventsCount += vkEventsResponse.getSubCommentByIdEventsCount();
        }

        SentryIssueResponse[] vkUserGroups = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_USER_GROUPS.value(), token());
        if (vkUserGroups.length == 1) {
            vkEventsResponse.setUserGroupsEventsCount(Long.parseLong(vkUserGroups[0].getCount()));
            allEventsCount += vkEventsResponse.getUserGroupsEventsCount();
        }

        SentryIssueResponse[] vkUserGroup = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_USER_GROUP.value(), token());
        if (vkUserGroup.length == 1) {
            vkEventsResponse.setUserGroupEventsCount(Long.parseLong(vkUserGroup[0].getCount()));
            allEventsCount += vkEventsResponse.getUserGroupEventsCount();
        }

        SentryIssueResponse[] vkPosts = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_POSTS.value(), token());
        if (vkPosts.length == 1) {
            vkEventsResponse.setPostsEventsCount(Long.parseLong(vkPosts[0].getCount()));
            allEventsCount += vkEventsResponse.getPostsEventsCount();
        }

        SentryIssueResponse[] vkPost = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.GET_POST.value(), token());
        if (vkPost.length == 1) {
            vkEventsResponse.setPostByIdEventsCount(Long.parseLong(vkPost[0].getCount()));
            allEventsCount += vkEventsResponse.getPostByIdEventsCount();
        }

        SentryIssueResponse[] vkAddPost = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.ADD_POST.value(), token());
        if (vkAddPost.length == 1) {
            vkEventsResponse.setAddPostEventsCount(Long.parseLong(vkAddPost[0].getCount()));
            allEventsCount += vkEventsResponse.getAddPostEventsCount();
        }

        SentryIssueResponse[] vkUpdatePost = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.UPDATE_POST.value(), token());
        if (vkUpdatePost.length == 1) {
            vkEventsResponse.setUpdatePostEventsCount(Long.parseLong(vkUpdatePost[0].getCount()));
            allEventsCount += vkEventsResponse.getUpdatePostEventsCount();
        }

        SentryIssueResponse[] vkDeletePost = client.getIssues("level:info server_name:" + SentryServerName.VK_ADAPTER.value() + " type:" + SentryVkTags.DELETE_POST.value(), token());
        if (vkDeletePost.length == 1) {
            vkEventsResponse.setDeletePostEventsCount(Long.parseLong(vkDeletePost[0].getCount()));
            allEventsCount += vkEventsResponse.getDeletePostEventsCount();
        }

        vkEventsResponse.setAllEventsCount(allEventsCount);

        return vkEventsResponse;
    }

    private FacebookEventsResponse findFacebookEvents() {
        FacebookEventsResponse facebookEventsResponse = new FacebookEventsResponse();

        SentryIssueResponse[] fbAllFacebookAccount = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_ALL_FACEBOOK_ACCOUNT.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_ALL_FACEBOOK_ACCOUNT.value());
        long allEventsCount = 0;
        if (fbAllFacebookAccount.length == 1) {
            facebookEventsResponse.setAllFacebookAccountEventsCount(Long.parseLong(fbAllFacebookAccount[0].getCount()));
            allEventsCount += facebookEventsResponse.getAllFacebookAccountEventsCount();
        }

        SentryIssueResponse[] fbSaveAccount = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.SAVE_ACCOUNT.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.SAVE_ACCOUNT.value());
        if (fbSaveAccount.length == 1) {
            facebookEventsResponse.setSaveAccountEventsCount(Long.parseLong(fbSaveAccount[0].getCount()));
            allEventsCount += facebookEventsResponse.getSaveAccountEventsCount();
        }

        SentryIssueResponse[] fbRemoveAccount = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.REMOVE_ACCOUNT.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.REMOVE_ACCOUNT.value());
        if (fbRemoveAccount.length == 1) {
            facebookEventsResponse.setRemoveAccountEventsCount(Long.parseLong(fbRemoveAccount[0].getCount()));
            allEventsCount += facebookEventsResponse.getRemoveAccountEventsCount();
        }

        SentryIssueResponse[] fbUrlForming = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.URL_FORMING.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.URL_FORMING.value());
        if (fbUrlForming.length == 1) {
            facebookEventsResponse.setUrlFormingEventsCount(Long.parseLong(fbUrlForming[0].getCount()));
            allEventsCount += facebookEventsResponse.getUrlFormingEventsCount();
        }

        SentryIssueResponse[] fbUserInfo = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_USER_INFO.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_USER_INFO.value());
        if (fbUserInfo.length == 1) {
            facebookEventsResponse.setUserInfoEventsCount(Long.parseLong(fbUserInfo[0].getCount()));
            allEventsCount += facebookEventsResponse.getUserInfoEventsCount();
        }

        SentryIssueResponse[] fbCommentsOfPost = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.COMMENTS_OF_POST.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.COMMENTS_OF_POST.value());
        if (fbCommentsOfPost.length == 1) {
            facebookEventsResponse.setCommentsOfPostEventsCount(Long.parseLong(fbCommentsOfPost[0].getCount()));
            allEventsCount += facebookEventsResponse.getCommentsOfPostEventsCount();
        }

        SentryIssueResponse[] fbCommentOfPost = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.COMMENT_OF_POST.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.COMMENT_OF_POST.value());
        if (fbCommentOfPost.length == 1) {
            facebookEventsResponse.setCommentOfPostEventsCount(Long.parseLong(fbCommentOfPost[0].getCount()));
            allEventsCount += facebookEventsResponse.getCommentOfPostEventsCount();
        }

        SentryIssueResponse[] fbSubComments = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_SUB_COMMENTS.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_SUB_COMMENTS.value());
        if (fbSubComments.length == 1) {
            facebookEventsResponse.setSubCommentsEventsCount(Long.parseLong(fbSubComments[0].getCount()));
            allEventsCount += facebookEventsResponse.getSubCommentsEventsCount();
        }

        SentryIssueResponse[] fbSumCommentById = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_SUB_COMMENT_BY_ID.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_SUB_COMMENT_BY_ID.value());
        if (fbSumCommentById.length == 1) {
            facebookEventsResponse.setSubCommentByIdEventsCount(Long.parseLong(fbSumCommentById[0].getCount()));
            allEventsCount += facebookEventsResponse.getSubCommentByIdEventsCount();
        }

        SentryIssueResponse[] fbUserGroups = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_USER_GROUPS.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_USER_GROUPS.value());
        if (fbUserGroups.length == 1) {
            facebookEventsResponse.setUserGroupsEventsCount(Long.parseLong(fbUserGroups[0].getCount()));
            allEventsCount += facebookEventsResponse.getUserGroupsEventsCount();
        }

        SentryIssueResponse[] fbUserGroup = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_USER_GROUP.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_USER_GROUP.value());
        if (fbUserGroup.length == 1) {
            facebookEventsResponse.setUserGroupEventsCount(Long.parseLong(fbUserGroup[0].getCount()));
            allEventsCount += facebookEventsResponse.getUserGroupEventsCount();
        }

        SentryIssueResponse[] fbPosts = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_POSTS.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_POSTS.value());
        if (fbPosts.length == 1) {
            facebookEventsResponse.setPostsEventsCount(Long.parseLong(fbPosts[0].getCount()));
            allEventsCount += facebookEventsResponse.getPostsEventsCount();
        }

        SentryIssueResponse[] fbPost = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_POST.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.GET_POST.value());
        if (fbPost.length == 1) {
            facebookEventsResponse.setPostByIdEventsCount(Long.parseLong(fbPost[0].getCount()));
            allEventsCount += facebookEventsResponse.getPostByIdEventsCount();
        }

        SentryIssueResponse[] fbAddPost = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.ADD_POST.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.ADD_POST.value());
        if (fbAddPost.length == 1) {
            facebookEventsResponse.setAddPostEventsCount(Long.parseLong(fbAddPost[0].getCount()));
            allEventsCount += facebookEventsResponse.getAddPostEventsCount();
        }

        SentryIssueResponse[] fbUpdatePost = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.UPDATE_POST.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.UPDATE_POST.value());
        if (fbUpdatePost.length == 1) {
            facebookEventsResponse.setUpdatePostEventsCount(Long.parseLong(fbUpdatePost[0].getCount()));
            allEventsCount += facebookEventsResponse.getUpdatePostEventsCount();
        }

        SentryIssueResponse[] fbDeletePost = client.getIssues("level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.DELETE_POST.value(), token());
        log.info("query: level:info server_name:" + SentryServerName.FB_ADAPTER.value() + " type:" + SentryFacebookTags.DELETE_POST.value());
        if (fbDeletePost.length == 1) {
            facebookEventsResponse.setDeletePostEventsCount(Long.parseLong(fbDeletePost[0].getCount()));
            allEventsCount += facebookEventsResponse.getDeletePostEventsCount();
        }

        facebookEventsResponse.setAllEventsCount(allEventsCount);

        return facebookEventsResponse;
    }

    public ErrorsStatResponse getErrorsStatSentry() {
        SentryIssueResponse[] authErrors = client.getIssues("server_name:" + SentryServerName.AUTH.value() + " level:error", token());
        long countAuthErrors = countErrors(authErrors);

        SentryIssueResponse[] vkAdapterErrors = client.getIssues("server_name:" + SentryServerName.VK_ADAPTER.value() + " level:error", token());
        long countVkAdapterErrors = countErrors(vkAdapterErrors);

        SentryIssueResponse[] fbAdapterErrors = client.getIssues("server_name:" + SentryServerName.FB_ADAPTER.value() + " level:error", token());
        long countFbAdapterErrors = countErrors(fbAdapterErrors);

        SentryIssueResponse[] workerErrors = client.getIssues("server_name:" + SentryServerName.WORKER.value() + " level:error", token());
        long countWorkerErrors = countErrors(workerErrors);

        SentryIssueResponse[] storageErrors = client.getIssues("server_name:" + SentryServerName.STORAGE.value() + " level:error", token());
        long countStorageErrors = countErrors(storageErrors);

        SentryIssueResponse[] bstatErrors = client.getIssues("server_name:" + SentryServerName.BSTAT.value() + " level:error", token());
        long countBstatErrors = countErrors(bstatErrors);

        SentryIssueResponse[] mailSenderErrors = client.getIssues("server_name:" + SentryServerName.MAIL_SENDER.value() + " level:error", token());
        long countMailSenderErrors = countErrors(mailSenderErrors);

        SentryIssueResponse[] gatewayErrors = client.getIssues("server_name:" + SentryServerName.GATEWAY.value() + " level:error", token());
        long countGatewayErrors = countErrors(gatewayErrors);

        SentryIssueResponse[] techSupportErrors = client.getIssues("server_name:" + SentryServerName.TECH_SUPPORT.value() + " level:error", token());
        long countTechSupportErrors = countErrors(techSupportErrors);

        SentryIssueResponse[] textAnalyzerErrors = client.getIssues("server_name:" + SentryServerName.TEXT_ANALYZER.value() + " level:error", token());
        long countTextAnalyzerErrors = countErrors(textAnalyzerErrors);

        return ErrorsStatResponse.builder()
                .authErrorsCount(countAuthErrors)
                .bstatErrorsCount(countBstatErrors)
                .fbAdapterErrorsCount(countFbAdapterErrors)
                .vkAdapterErrorsCount(countVkAdapterErrors)
                .gatewayErrorsCount(countGatewayErrors)
                .mailSenderErrorsCount(countMailSenderErrors)
                .storageErrorsCount(countStorageErrors)
                .techSupportErrorsCount(countTechSupportErrors)
                .textAnalyzerErrorsCount(countTextAnalyzerErrors)
                .workerErrorsCount(countWorkerErrors)
                .allErrorsCount(countAuthErrors + countBstatErrors + countFbAdapterErrors + countVkAdapterErrors +
                        countGatewayErrors + countMailSenderErrors + countStorageErrors + countTechSupportErrors +
                        countTextAnalyzerErrors + countWorkerErrors)
                .build();
    }

    private UsingSocialNetworkResponse usingSocialNetworkResponse = UsingSocialNetworkResponse.builder().build();
    private ErrorsStatResponse errorsStatResponse = ErrorsStatResponse.builder().build();
    private List<UsersStatResponse> onlineStatResponse = new ArrayList<>();
    private List<UsersStatResponse> activeStatResponse = new ArrayList<>();
    private List<UsersStatResponse> newStatResponse = new ArrayList<>();
    private List<UsersStatResponse> allStatResponse = new ArrayList<>();

    @Scheduled(fixedDelay = 30000)
    public void setUsingSNR() {
        usingSocialNetworkResponse = getUsingSocialNetworkSentry();
    }

    @Scheduled(fixedDelay = 30000)
    public void setEST() {
        errorsStatResponse = getErrorsStatSentry();
    }

    @Scheduled(fixedDelay = 3600000)
    public void setUsersCountTimeline() {
        onlineStatResponse = getOnlineUsersStatTimelineSentry();
        activeStatResponse = getActiveUsersStatTimelineSentry();
        newStatResponse = getNewUsersStatTimelineSentry();
        allStatResponse = getAllUsersStatTimelineSentry();
    }
}
