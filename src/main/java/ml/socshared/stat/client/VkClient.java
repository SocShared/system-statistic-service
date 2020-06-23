package ml.socshared.stat.client;

import ml.socshared.stat.security.response.CountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "vk-client", url = "${feign.url.vk:}")
public interface VkClient {

    @GetMapping("/api/v1/private/app/count")
    CountResponse appCount(@RequestHeader("Authorization") String token);

}
