package br.com.vini.orderapi.api.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Erro padrão da API
 * 
 * @author viniciosarodrigues
 * 
 * @see ErrorItem
 *
 */
public class StandardErrorAPI implements Serializable {

    private static final long serialVersionUID = 7605476013709721447L;

    private Long timestamp;
    private Integer status;
    private String error;
    private List<ErrorItem> validations = new ArrayList<>();
    private String message;
    private String path;

    public StandardErrorAPI(Long timestamp, Integer status, String error, List<ErrorItem> validations, String message, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.validations = validations;
        this.message = message;
        this.path = path;
    }

    public StandardErrorAPI() {
        super();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ErrorItem> getValidations() {
        return validations;
    }

    public void setValidations(List<ErrorItem> validations) {
        this.validations = validations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, message, path, status, timestamp, validations);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StandardErrorAPI other = (StandardErrorAPI) obj;
        return Objects.equals(error, other.error) && Objects.equals(message, other.message) && Objects.equals(path, other.path)
                && Objects.equals(status, other.status) && Objects.equals(timestamp, other.timestamp)
                && Objects.equals(validations, other.validations);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StandardErrorAPI [timestamp=");
        builder.append(timestamp);
        builder.append(", status=");
        builder.append(status);
        builder.append(", error=");
        builder.append(error);
        builder.append(", validations=");
        builder.append(validations);
        builder.append(", message=");
        builder.append(message);
        builder.append(", path=");
        builder.append(path);
        builder.append("]");
        return builder.toString();
    }

}
