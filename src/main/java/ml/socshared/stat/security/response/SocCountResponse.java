package ml.socshared.stat.security.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocCountResponse {

    private long vkAccountCount;
    private long fbAccountCount;
    private long facebookGroupCount;
    private long vkGroupCount;
    private long publishedCount;
    private long notSuccessfulCount;
    private long waitingOrProcessingCount;
    private long vkPublicationCount;
    private long fbPublicationCount;

}
