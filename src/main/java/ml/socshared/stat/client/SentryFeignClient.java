package ml.socshared.stat.client;

import ml.socshared.stat.domain.response.SentryIssueResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sentry-client", url = "${sentry.url:}")
public interface SentryFeignClient {

    @GetMapping(value = "/api/0/projects/socshared/socshared-service/issues/")
    SentryIssueResponse[] getIssues(@RequestParam(name = "query", required = false) String query,
                                    @RequestHeader("Authorization") String token);

}
