package ml.socshared.stat.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalStatsResponse {

    private Long usingFacebookPercent;
    private Long usingVkPercent;
    private Long fbAccountCount;
    private Long vkAccountCount;
    private Long fbGroupCount;
    private Long vkGroupCount;
    private Long publishedPublicationCount;
    private Long notSuccessfulPublicationCount;
    private Long waitingOrProcessingPublicationCount;
    private Long fbPublicationCount;
    private Long vkPublicationCount;
    private Long allUsersCount;
    private Long newUsersCount;
    private Long activeUsersCount;
    private Long onlineUsersCount;
    private Long errorsCount;

}
