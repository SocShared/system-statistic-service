package ml.socshared.stat.domain.response.userstat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersStatResponse {

    private Long onlineUsers;
    private Long newUsers;
    private Long activeUsers;
    private Long allUsers;

}
