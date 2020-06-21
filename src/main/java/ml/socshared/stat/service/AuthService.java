package ml.socshared.stat.service;

import ml.socshared.stat.domain.response.RestResponsePage;
import ml.socshared.stat.domain.response.UserResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;

public interface AuthService {

    UsersStatResponse activeUsersCount();
    RestResponsePage<UserResponse> getActiveUsers(Integer page, Integer size);
    UsersStatResponse onlineUsersCount();
    RestResponsePage<UserResponse> getOnlineUsers(Integer page, Integer size);
    UsersStatResponse newUsersCount();
    RestResponsePage<UserResponse> getNewUsers(Integer page, Integer size);
    UsersStatResponse allUsersCount();
    RestResponsePage<UserResponse> getAllUsers(Integer page, Integer size);

}
