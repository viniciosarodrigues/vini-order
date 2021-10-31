package br.com.vini.orderapi.api.exception;

import java.io.Serializable;
import java.util.Objects;

/**
 * Item de erro utilizado na listagem de erros de validações
 * 
 * @author viniciosarodrigues
 *
 */
public class ErrorItem implements Serializable {

    private static final long serialVersionUID = 2742619349592940845L;

    private String fieldName;
    private String message;

    public ErrorItem() {
        super();
    }

    public ErrorItem(String fieldName, String message) {
        super();
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorItem other = (ErrorItem) obj;
        return Objects.equals(fieldName, other.fieldName) && Objects.equals(message, other.message);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ErrorItem [fieldName=");
        builder.append(fieldName);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }

}