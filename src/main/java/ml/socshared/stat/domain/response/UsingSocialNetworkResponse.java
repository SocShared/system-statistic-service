package ml.socshared.stat.domain.response;

import lombok.Builder;
import lombok.Data;
import ml.socshared.stat.domain.response.usingsocial.FacebookEventsResponse;
import ml.socshared.stat.domain.response.usingsocial.VkEventsResponse;

@Data
@Builder
public class UsingSocialNetworkResponse {

    private VkEventsResponse vk;
    private FacebookEventsResponse facebook;

}
