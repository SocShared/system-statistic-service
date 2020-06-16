package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.UsingSocialNetworkResponse;
import ml.socshared.stat.service.SentryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ml.socshared.stat.api.v1.rest.SentryApi;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class SentryController implements SentryApi {

    private final SentryService service;

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/issues")
    public SentryIssueResponse[] getIssues(@RequestParam(name = "query", required = false) String query) {
        return service.getIssues(query);
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/social")
    public UsingSocialNetworkResponse getUsingSocialNetworkStat() {
        return service.getUsingSocialNetwork();
    }

}
