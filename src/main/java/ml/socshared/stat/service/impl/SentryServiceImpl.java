package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.client.SentryFeignClient;
import ml.socshared.stat.service.SentryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentryServiceImpl implements SentryService {

    private final SentryFeignClient client;

}
