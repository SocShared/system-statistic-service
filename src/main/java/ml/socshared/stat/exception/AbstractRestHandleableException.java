package ml.socshared.stat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AbstractRestHandleableException extends RuntimeException implements HttpStatusCodeContainer {

    private final HttpStatus httpStatus;

    public AbstractRestHandleableException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public AbstractRestHandleableException(HttpStatus httpStatus) {
        this("", httpStatus);
    }

    public AbstractRestHandleableException(Throwable throwable, HttpStatus httpStatus) {
        super(throwable);
        this.httpStatus = httpStatus;
    }
}
