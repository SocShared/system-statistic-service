package ml.socshared.stat.client;

import ml.socshared.stat.domain.response.RestResponsePage;
import ml.socshared.stat.domain.response.SuccessResponse;
import ml.socshared.stat.domain.response.UserResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.security.request.CheckTokenRequest;
import ml.socshared.stat.security.request.ServiceTokenRequest;
import ml.socshared.stat.security.response.ServiceTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-client", url = "${feign.url.auth:}")
public interface AuthClient {

    @PostMapping(value = "/api/v1/public/service/validate_token", produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessResponse send(CheckTokenRequest request);

    @PostMapping(value = "/api/v1/public/service/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ServiceTokenResponse getServiceToken(@RequestBody ServiceTokenRequest request);

    @GetMapping(value = "/api/v1/private/users/online/count")
    UsersStatResponse getOnlineUsersCount(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/online")
    RestResponsePage<UserResponse> getOnlineUsers(@RequestParam(name = "page") Integer page,
                                                  @RequestParam(name = "size") Integer size,
                                                  @RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/active/count")
    UsersStatResponse getActiveUsersCount(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/active")
    RestResponsePage<UserResponse> getActiveUsers(@RequestParam(name = "page") Integer page,
                                                  @RequestParam(name = "size") Integer size,
                                                  @RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/new/count")
    UsersStatResponse getNewUsersCount(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/new")
    RestResponsePage<UserResponse> getNewUsers(@RequestParam(name = "page") Integer page,
                                               @RequestParam(name = "size") Integer size,
                                               @RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/all/count")
    UsersStatResponse getAllUsersCount(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/v1/private/users/all")
    RestResponsePage<UserResponse> getAllUsers(@RequestParam(name = "page") Integer page,
                                               @RequestParam(name = "size") Integer size,
                                               @RequestHeader("Authorization") String token);
}
