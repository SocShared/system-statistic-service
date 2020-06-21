package ml.socshared.stat.controller.v1;

import lombok.RequiredArgsConstructor;
import ml.socshared.stat.domain.response.RestResponsePage;
import ml.socshared.stat.domain.response.UserResponse;
import ml.socshared.stat.domain.response.userstat.UsersStatResponse;
import ml.socshared.stat.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/online/count")
    public UsersStatResponse getOnlineUsersStat() {
        return authService.onlineUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/online")
    public RestResponsePage<UserResponse> getOnlineUsers(@RequestParam(name = "page") Integer page,
                                                         @RequestParam(name = "size") Integer size) {
        return authService.getOnlineUsers(page, size);
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/active/count")
    public UsersStatResponse getActiveUsersStat() {
        return authService.activeUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/active")
    public RestResponsePage<UserResponse> getActiveUsers(@RequestParam(name = "page") Integer page,
                                                         @RequestParam(name = "size") Integer size) {
        return authService.getActiveUsers(page, size);
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/new/count")
    public UsersStatResponse getNewUsersStat() {
        return authService.newUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/new")
    public RestResponsePage<UserResponse> getNewUsers(@RequestParam(name = "page") Integer page,
                                                      @RequestParam(name = "size") Integer size) {
        return authService.getNewUsers(page, size);
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/all/count")
    public UsersStatResponse getAllUsersStat() {
        return authService.allUsersCount();
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping(value = "/private/stat/users/all")
    public RestResponsePage<UserResponse> getAllUsers(@RequestParam(name = "page") Integer page,
                                                      @RequestParam(name = "size") Integer size) {
        return authService.getAllUsers(page, size);
    }

}
