package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.TotalStatsResponse;
import ml.socshared.stat.domain.response.errorstat.ErrorsStatResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.domain.response.usingsocial.UsingSocialNetworkResponse;
import ml.socshared.stat.security.response.SocCountResponse;
import ml.socshared.stat.service.AuthService;
import ml.socshared.stat.service.SentryService;
import ml.socshared.stat.service.SocService;
import ml.socshared.stat.service.TotalStatsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TotalStatsServiceImpl implements TotalStatsService {

    private final AuthService authService;
    private final SentryService sentryService;
    private final SocService socService;

    @Override
    public TotalStatsResponse totalStats() {
        UsingSocialNetworkResponse usingSocialNetworkResponse = sentryService.getUsingSocialNetwork();

        long allEventUsingSocial = usingSocialNetworkResponse.getFacebook().getAllEventsCount() +
                usingSocialNetworkResponse.getVk().getAllEventsCount();

        long usingFacebookPercent = Math.round(1.0 * usingSocialNetworkResponse.getFacebook().getAllEventsCount() / allEventUsingSocial * 100);
        long usingVkPercent = Math.round(1.0 * usingSocialNetworkResponse.getVk().getAllEventsCount() / allEventUsingSocial * 100);

        ErrorsStatResponse errorsStatResponse = sentryService.getErrorsStat();

        SocCountResponse socCountResponse = socService.count();

        return TotalStatsResponse.builder()
                .usingFacebookPercent(usingFacebookPercent)
                .usingVkPercent(usingVkPercent)
                .errorsCount(errorsStatResponse.getAllErrorsCount())
                .fbAccountCount(socCountResponse.getFbAccountCount())
                .vkAccountCount(socCountResponse.getVkAccountCount())
                .fbGroupCount(socCountResponse.getFacebookGroupCount())
                .vkGroupCount(socCountResponse.getVkGroupCount())
                .publishedPublicationCount(socCountResponse.getPublishedCount())
                .notSuccessfulPublicationCount(socCountResponse.getNotSuccessfulCount())
                .waitingOrProcessingPublicationCount(socCountResponse.getWaitingOrProcessingCount())
                .fbPublicationCount(socCountResponse.getFbPublicationCount())
                .vkPublicationCount(socCountResponse.getVkPublicationCount())
                .onlineUsersCount(authService.onlineUsersCount().getOnlineUsers())
                .activeUsersCount(authService.activeUsersCount().getActiveUsers())
                .newUsersCount(authService.newUsersCount().getNewUsers())
                .allUsersCount(authService.allUsersCount().getAllUsers())
                .build();
    }
}
