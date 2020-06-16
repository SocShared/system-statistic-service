package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.client.SentryFeignClient;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.service.SentryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
}
