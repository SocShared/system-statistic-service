package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.errorstat.ErrorsStatResponse;
import ml.socshared.stat.domain.response.SentryIssueResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.domain.response.usingsocial.UsingSocialNetworkResponse;
import ml.socshared.stat.service.AuthService;
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

    private final AuthService authService;
    private final SentryService sentryService;

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/issues")
    public SentryIssueResponse[] getIssues(@RequestParam(name = "query", required = false) String query) {
        return sentryService.getIssues(query);
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/social")
    public UsingSocialNetworkResponse getUsingSocialNetworkStat() {
        return sentryService.getUsingSocialNetwork();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/online/count")
    public UsersStatResponse getOnlineUsersStat() {
        return authService.onlineUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/online/count/timeline")
    public List<UsersStatResponse> getOnlineUsersStatTimeline() {
        return sentryService.getOnlineUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/active/count")
    public UsersStatResponse getActiveUsersStat() {
        return authService.activeUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/active/count/timeline")
    public List<UsersStatResponse> getActiveUsersStatTimeline() {
        return sentryService.getActiveUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/new/count")
    public UsersStatResponse getNewUsersStat() {
        return authService.newUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/new/count/timeline")
    public List<UsersStatResponse> getNewUsersStatTimeline() {
        return sentryService.getNewUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/all/count")
    public UsersStatResponse getAllUsersStat() {
        return authService.allUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/all/count/timeline")
    public List<UsersStatResponse> getAllUsersStatTimeline() {
        return sentryService.getAllUsersStatTimeline();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/errors")
    public ErrorsStatResponse getErrorsStat() {
        return sentryService.getErrorsStat();
    }

}
