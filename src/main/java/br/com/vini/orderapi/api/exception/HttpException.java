package br.com.vini.orderapi.api.exception;

import org.springframework.http.HttpStatus;

/**
 * Erro padra da aplicação, usar apenas este erro para lançamento de qualquer exceção.
 * 
 * @author viniciosarodrigues
 *
 */
public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 6346915435058859173L;

    private HttpStatus status;
    private String message;

    public HttpException() {
        super();
    }

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
