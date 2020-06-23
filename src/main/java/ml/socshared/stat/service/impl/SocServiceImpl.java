package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.client.FacebookClient;
import ml.socshared.stat.client.StorageClient;
import ml.socshared.stat.client.VkClient;
import ml.socshared.stat.security.model.TokenObject;
import ml.socshared.stat.security.response.CountResponse;
import ml.socshared.stat.security.response.GroupCountResponse;
import ml.socshared.stat.security.response.PublicationCountResponse;
import ml.socshared.stat.security.response.SocCountResponse;
import ml.socshared.stat.service.SocService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocServiceImpl implements SocService {

    private final StorageClient storageClient;
    private final VkClient vkClient;
    private final FacebookClient facebookClient;

    @Value("#{tokenGetter.tokenStorageService}")
    private TokenObject tokenStorageService;

    @Value("#{tokenGetter.tokenVK}")
    private TokenObject tokenVK;

    @Value("#{tokenGetter.tokenFB}")
    private TokenObject tokenFB;

    @Override
    public SocCountResponse count() {
        CountResponse fbAccount = facebookClient.accountCount("Bearer " + tokenFB.getToken());
        CountResponse vkAccount = vkClient.appCount("Bearer " + tokenVK.getToken());
        GroupCountResponse groupCountResponse = storageClient.groupCount("Bearer " + tokenStorageService.getToken());
        PublicationCountResponse publicationCountResponse = storageClient.publicationsCount("Bearer " + tokenStorageService.getToken());

        return SocCountResponse.builder()
                .fbAccountCount(fbAccount.getCount())
                .vkAccountCount(vkAccount.getCount())
                .facebookGroupCount(groupCountResponse.getFacebookGroupCount())
                .vkGroupCount(groupCountResponse.getVkGroupCount())
                .fbPublicationCount(publicationCountResponse.getFbPublicationCount())
                .vkPublicationCount(publicationCountResponse.getVkPublicationCount())
                .notSuccessfulCount(publicationCountResponse.getNotSuccessfulCount())
                .publishedCount(publicationCountResponse.getPublishedCount())
                .waitingOrProcessingCount(publicationCountResponse.getWaitingOrProcessingCount())
                .build();
    }
}
