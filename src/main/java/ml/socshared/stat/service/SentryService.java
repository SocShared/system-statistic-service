package ml.socshared.stat.service;

import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.UsingSocialNetworkResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;

public interface SentryService {

    SentryIssueResponse[] getIssues(String query);
    UsingSocialNetworkResponse getUsingSocialNetwork();
    UsersStatResponse getUsersStat();

}
