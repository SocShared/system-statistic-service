package ml.socshared.stat.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.RestResponsePage;
import ml.socshared.stat.domain.response.UserResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.client.AuthClient;
import ml.socshared.stat.security.model.TokenObject;
import ml.socshared.stat.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    @Value("#{tokenGetter.tokenAuth}")
    private TokenObject tokenAuth;

    private String tokenAuth() {
        return "Bearer " + tokenAuth.getToken();
    }

    @Override
    public UsersStatResponse activeUsersCount() {
        return authClient.getActiveUsersCount(tokenAuth());
    }

    @Override
    public UsersStatResponse onlineUsersCount() {
        return authClient.getOnlineUsersCount(tokenAuth());
    }

    @Override
    public UsersStatResponse newUsersCount() {
        return authClient.getNewUsersCount(tokenAuth());
    }

    @Override
    public UsersStatResponse allUsersCount() {
        return authClient.getAllUsersCount(tokenAuth());
    }

    @Override
    public RestResponsePage<UserResponse> getActiveUsers(Integer page, Integer size) {
        return authClient.getActiveUsers(page, size, tokenAuth());
    }

    @Override
    public RestResponsePage<UserResponse> getOnlineUsers(Integer page, Integer size) {
        return authClient.getOnlineUsers(page, size, tokenAuth());
    }

    @Override
    public RestResponsePage<UserResponse> getNewUsers(Integer page, Integer size) {
        return authClient.getNewUsers(page, size, tokenAuth());
    }

    @Override
    public RestResponsePage<UserResponse> getAllUsers(Integer page, Integer size) {
        return authClient.getAllUsers(page, size, tokenAuth());
    }
}
