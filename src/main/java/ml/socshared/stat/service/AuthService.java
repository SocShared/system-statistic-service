package ml.socshared.stat.service;

import ml.socshared.stat.domain.response.userstat.UsersStatResponse;

public interface AuthService {

    UsersStatResponse activeUsersCount();
    UsersStatResponse onlineUsersCount();
    UsersStatResponse newUsersCount();
    UsersStatResponse allUsersCount();

}
