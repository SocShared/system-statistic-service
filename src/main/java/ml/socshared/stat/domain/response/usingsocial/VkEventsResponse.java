package ml.socshared.stat.domain.response.usingsocial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VkEventsResponse {

    private Long allEventsCount;
    private Long userInfoEventsCount;
    private Long commentsOfPostEventsCount;
    private Long commentOfPostEventsCount;
    private Long subCommentsEventsCount;
    private Long subCommentByIdEventsCount;
    private Long userGroupsEventsCount;
    private Long userGroupEventsCount;
    private Long postsEventsCount;
    private Long postByIdEventsCount;
    private Long addPostEventsCount;
    private Long updatePostEventsCount;
    private Long deletePostEventsCount;

}
