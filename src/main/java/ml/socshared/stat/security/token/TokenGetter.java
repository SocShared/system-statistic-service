package ml.socshared.stat.security.token;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ml.socshared.stat.security.jwt.JwtTokenProvider;
import ml.socshared.stat.security.model.TokenObject;
import ml.socshared.stat.security.request.ServiceTokenRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@Aspect
@Getter
public class TokenGetter {

    private final JwtTokenProvider jwtTokenProvider;

    public TokenGetter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        init();
    }

    private TokenObject tokenAuth;

    private void init() {
        tokenAuth = new TokenObject();
    }

    @Before("execution(* ml.socshared.stat.service.impl.AuthServiceImpl.*(..))")
    public TokenObject initTokenAuth() {
        if (tokenAuth.getToken() != null && jwtTokenProvider.validateServiceToken(tokenAuth.getToken())) {
            return tokenAuth;
        }

        ServiceTokenRequest request = new ServiceTokenRequest();
        request.setFromServiceId(UUID.fromString("eeb4585c-1d8f-463c-b441-e5dbb27ec94d"));
        request.setToServiceId(UUID.fromString("58c2b3d5-dfad-41af-9451-d0a26fdc9019"));
        request.setToSecretService(UUID.fromString("0cb9bb2e-ee6a-48b7-b36a-23fb07f3fa28"));

        this.tokenAuth.setToken(jwtTokenProvider.buildServiceToken(request).getToken());
        log.debug(tokenAuth.getToken());
        return tokenAuth;
    }

}
