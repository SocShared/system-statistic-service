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
    private TokenObject tokenStorageService;
    private TokenObject tokenFB;
    private TokenObject tokenVK;

    private void init() {
        tokenAuth = new TokenObject();
        tokenStorageService = new TokenObject();
        tokenFB = new TokenObject();
        tokenVK = new TokenObject();
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

    @Before("execution(* ml.socshared.stat.service.impl.SocServiceImpl.*(..))")
    public TokenObject initTokenStorageService() {
        if (tokenStorageService != null && jwtTokenProvider.validateServiceToken(tokenStorageService.getToken())) {
            return tokenStorageService;
        }

        ServiceTokenRequest request = new ServiceTokenRequest();
        request.setFromServiceId(UUID.fromString("eeb4585c-1d8f-463c-b441-e5dbb27ec94d"));
        request.setToServiceId(UUID.fromString("64141ce5-5604-4ade-ada2-e38cf7d2522c"));
        request.setToSecretService(UUID.fromString("5b21977e-166f-471b-a7a7-c60b20e18cf9"));

        this.tokenStorageService.setToken(jwtTokenProvider.buildServiceToken(request).getToken());

        return tokenStorageService;
    }

    @Before("execution(* ml.socshared.stat.service.impl.SocServiceImpl.*(..))")
    public TokenObject initTokenFB() {
        if (tokenFB.getToken() != null && jwtTokenProvider.validateServiceToken(tokenFB.getToken())) {
            log.debug(tokenFB.getToken());
            return tokenFB;
        }

        ServiceTokenRequest request = new ServiceTokenRequest();
        request.setFromServiceId(UUID.fromString("eeb4585c-1d8f-463c-b441-e5dbb27ec94d"));
        request.setToServiceId(UUID.fromString("f7e14d85-415c-4ab9-b285-a6481d79f507"));
        request.setToSecretService(UUID.fromString("427d82bb-b367-40b4-bee8-b18e32480899"));

        this.tokenFB.setToken(jwtTokenProvider.buildServiceToken(request).getToken());
        log.debug(tokenFB.getToken());
        return tokenFB;
    }

    @Before("execution(* ml.socshared.stat.service.impl.SocServiceImpl.*(..))")
    public TokenObject initTokenVK() {
        if (tokenVK != null && jwtTokenProvider.validateServiceToken(tokenVK.getToken())) {
            return tokenVK;
        }

        ServiceTokenRequest request = new ServiceTokenRequest();
        request.setFromServiceId(UUID.fromString("eeb4585c-1d8f-463c-b441-e5dbb27ec94d"));
        request.setToServiceId(UUID.fromString("cb43eee3-3468-4cc2-b6ed-63419e8726ce"));
        request.setToSecretService(UUID.fromString("f769cb1c-bf08-478d-8218-0bb347369dd7"));

        this.tokenVK.setToken(jwtTokenProvider.buildServiceToken(request).getToken());

        return tokenVK;
    }

}
