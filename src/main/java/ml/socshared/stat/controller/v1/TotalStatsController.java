package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.TotalStatsResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.service.TotalStatsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class TotalStatsController {

    private final TotalStatsService totalStatsService;

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/total")
    public TotalStatsResponse getOnlineUsersStat() {
        return totalStatsService.totalStats();
    }

}
