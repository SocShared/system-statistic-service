package ml.socshared.stat.domain.response.userstat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersStatResponse {

    private long onlineUsers;
    private long newUsers;
    private long activeUsers;
    private long allUsers;

}
