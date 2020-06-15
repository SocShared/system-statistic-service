package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.service.SentryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ml.socshared.stat.api.v1.rest.SentryApi;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class SentryController implements SentryApi {

    private final SentryService service;

}
