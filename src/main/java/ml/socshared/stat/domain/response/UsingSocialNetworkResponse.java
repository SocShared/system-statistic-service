package ml.socshared.stat.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsingSocialNetworkResponse {

    private Long vkEventsCount;
    private Long fbEventsCount;

}
