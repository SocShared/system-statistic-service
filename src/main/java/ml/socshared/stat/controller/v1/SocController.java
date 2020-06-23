package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.security.response.SocCountResponse;
import ml.socshared.stat.service.SocService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class SocController {

    private final SocService socService;

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/soc/count")
    public SocCountResponse getSocCount() {
        return socService.count();
    }

}
