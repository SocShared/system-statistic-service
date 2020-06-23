package ml.socshared.stat.client;

import ml.socshared.stat.security.response.CountResponse;
import ml.socshared.stat.security.response.GroupCountResponse;
import ml.socshared.stat.security.response.PublicationCountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="storage-client", url = "${feign.url.storage:}")
public interface StorageClient {

    @GetMapping("/api/v1/private/groups/count")
    GroupCountResponse groupCount(@RequestHeader("Authorization") String token);

    @GetMapping("/api/v1/private/publications/count")
    PublicationCountResponse publicationsCount(@RequestHeader("Authorization") String token);

}
