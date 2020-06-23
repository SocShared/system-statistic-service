package ml.socshared.stat.client;

import ml.socshared.stat.security.response.CountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "fb-client", url = "${feign.url.fb:}")
public interface FacebookClient {

    @GetMapping("/api/v1/private/account/count")
    CountResponse accountCount(@RequestHeader("Authorization") String token);

}
