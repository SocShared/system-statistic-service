package ml.socshared.stat.service;

import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.UsingSocialNetworkResponse;
import ml.socshared.stat.domain.response.errorstat.ErrorStatResponse;
import ml.socshared.stat.domain.response.errorstat.ErrorsStatResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;

import java.util.List;

public interface SentryService {

    SentryIssueResponse[] getIssues(String query);
    UsingSocialNetworkResponse getUsingSocialNetwork();
    UsersStatResponse getOnlineUsersStat();
    List<UsersStatResponse> getOnlineUsersStatTimeline();
    UsersStatResponse getActiveUsersStat();
    List<UsersStatResponse> getActiveUsersStatTimeline();
    UsersStatResponse getNewUsersStat();
    List<UsersStatResponse> getNewUsersStatTimeline();
    UsersStatResponse getAllUsersStat();
    List<UsersStatResponse> getAllUsersStatTimeline();
    ErrorsStatResponse getErrorsStat();
    List<ErrorStatResponse> getErrorStatTimeline();

}
