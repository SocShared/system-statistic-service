package ml.socshared.stat.service;

import ml.socshared.stat.domain.response.SentryIssueResponse;

public interface SentryService {

    SentryIssueResponse[] getIssues(String query);

}
