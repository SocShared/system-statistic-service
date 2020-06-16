package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.client.SentryFeignClient;
import ml.socshared.stat.domain.enums.SentryServerName;
import ml.socshared.stat.domain.enums.tags.SentryVkTags;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.UsingSocialNetworkResponse;
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

    @Override
    public UsingSocialNetworkResponse getUsingSocialNetwork() {
        SentryIssueResponse[] vkEvents = client.getIssues("server_name:" + SentryServerName.VK_ADAPTER + " level:info", token());
        SentryIssueResponse[] fbEvents = client.getIssues("server_name:" + SentryServerName.FB_ADAPTER + " level:info", token());

        long vkEventsCount = 0L;
        for (SentryIssueResponse response : vkEvents) {
            vkEventsCount += Long.parseLong(response.getCount());
        }

        long fbEventsCount = 0L;
        for (SentryIssueResponse response : fbEvents) {
            fbEventsCount += Long.parseLong(response.getCount());
        }

        return UsingSocialNetworkResponse.builder()
                .fbEventsCount(fbEventsCount)
                .vkEventsCount(vkEventsCount)
                .build();
    }
}
