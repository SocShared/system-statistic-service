package ml.socshared.stat.exception.impl;

import ml.socshared.stat.exception.AbstractRestHandleableException;
import org.springframework.http.HttpStatus;

public class HttpBadGatewayException extends AbstractRestHandleableException {
    public HttpBadGatewayException() {
        super(HttpStatus.BAD_GATEWAY);
    }

    public HttpBadGatewayException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public HttpBadGatewayException(String message) {
        super(message, HttpStatus.BAD_GATEWAY);
    }
}