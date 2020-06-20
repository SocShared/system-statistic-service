package ml.socshared.stat.domain.response.errorstat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import ml.socshared.stat.config.CustomLocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorStatResponse {

    private Long errorsCount;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

}
