package ml.socshared.stat.domain.response.userstat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersStatResponse {

    private Long onlineUsers;
    private Long newUsers;
    private Long activeUsers;
    private Long allUsers;

}
