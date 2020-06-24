package ml.socshared.stat.domain.response.errorstat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorsStatResponse {

    private Long allErrorsCount;
    private Long authErrorsCount;
    private Long bstatErrorsCount;
    private Long textAnalyzerErrorsCount;
    private Long vkAdapterErrorsCount;
    private Long fbAdapterErrorsCount;
    private Long techSupportErrorsCount;
    private Long workerErrorsCount;
    private Long mailSenderErrorsCount;
    private Long gatewayErrorsCount;
    private Long storageErrorsCount;

}
