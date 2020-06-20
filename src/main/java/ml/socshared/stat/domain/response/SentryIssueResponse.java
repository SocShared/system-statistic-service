package ml.socshared.stat.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import ml.socshared.stat.config.CustomLocalDateTimeSerializer;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SentryIssueResponse {

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime lastSeen;
    private Long numComments;
    private Long userCount;
    private StatsResponse stats;
    private String culprit;
    private String title;
    private String id;
    private String type;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime firstSeen;
    private String count;
    private Object metadata;

}
