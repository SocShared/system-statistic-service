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
public class SentryEventResponse {

    private String eventID;
    private String projectID;
    private String groupID;
    private String message;
    private String title;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dateCreated;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dateReceived;
    private Object context;
    private Object tags;
    private Long size;

}
