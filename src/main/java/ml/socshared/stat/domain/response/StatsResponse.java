package ml.socshared.stat.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsResponse {

    @JsonProperty(value = "24h")
    private Integer[][] hours24;

}
