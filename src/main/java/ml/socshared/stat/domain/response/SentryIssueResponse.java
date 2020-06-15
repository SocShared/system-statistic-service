package ml.socshared.stat.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SentryIssueResponse {

    private String id;
    private Long numComments;
    private Long userCount;
    private String culprit;
    private String title;


}
