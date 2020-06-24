package ml.socshared.stat.service;

import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.usingsocial.UsingSocialNetworkResponse;
import ml.socshared.stat.domain.response.errorstat.ErrorsStatResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;

import java.util.List;

public interface SentryService {

    SentryIssueResponse[] getIssues(String query);
    UsingSocialNetworkResponse getUsingSocialNetwork();
    List<UsersStatResponse> getOnlineUsersStatTimeline();
    List<UsersStatResponse> getActiveUsersStatTimeline();
    List<UsersStatResponse> getNewUsersStatTimeline();
    List<UsersStatResponse> getAllUsersStatTimeline();
    ErrorsStatResponse getErrorsStat();

}
