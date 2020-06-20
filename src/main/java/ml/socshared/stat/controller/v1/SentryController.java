package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.errorstat.ErrorsStatResponse;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.domain.response.UsingSocialNetworkResponse;
import ml.socshared.stat.service.SentryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ml.socshared.stat.api.v1.rest.SentryApi;

import java.util.List;

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

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/online")
    public UsersStatResponse getOnlineUsersStat() {
        return service.getOnlineUsersStat();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/online/timeline")
    public List<UsersStatResponse> getOnlineUsersStatTimeline() {
        return service.getOnlineUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/active")
    public UsersStatResponse getActiveUsersStat() {
        return service.getActiveUsersStat();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/active/timeline")
    public List<UsersStatResponse> getActiveUsersStatTimeline() {
        return service.getActiveUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/new")
    public UsersStatResponse getNewUsersStat() {
        return service.getNewUsersStat();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/new/timeline")
    public List<UsersStatResponse> getNewUsersStatTimeline() {
        return service.getNewUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/all")
    public UsersStatResponse getAllUsersStat() {
        return service.getAllUsersStat();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/all/timeline")
    public List<UsersStatResponse> getAllUsersStatTimeline() {
        return service.getAllUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/errors")
    public ErrorsStatResponse getErrorsStat() {
        return service.getErrorsStat();
    }

}
