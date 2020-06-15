package ml.socshared.stat.client;

import ml.socshared.stat.domain.response.SentryIssueResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sentry-client", url = "${sentry.url:}")
public interface SentryFeignClient {

   // @GetMapping(value = "/api/v1/test", produces = MediaType.APPLICATION_JSON_VALUE)
  //  SentryIssueResponse

}
