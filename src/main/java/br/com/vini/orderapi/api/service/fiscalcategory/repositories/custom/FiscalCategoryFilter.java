package br.com.vini.orderapi.api.service.fiscalcategory.repositories.custom;

import org.springframework.http.HttpStatus;

import br.com.vini.orderapi.api.exception.HttpException;
import br.com.vini.orderapi.api.service.ApiFilter;

/**
 * Filtro customizado para consulta paginada de categorias fiscais
 * 
 * @author viniciosarodrigues
 *
 */
public class FiscalCategoryFilter extends ApiFilter {

    private static final long serialVersionUID = -7454373995462333969L;

    private String id;
    private String description;

    public Long getId() {
        try {
            if (id == null)
                return null;
            else
                return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "O identificador informado não é válido, verifique e tente novamente.");
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description == null ? "%%" : "%" + description + "%";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FiscalCategoryFilter [id=");
        builder.append(id);
        builder.append(", description=");
        builder.append(description);
        builder.append("]");
        return builder.toString();
    }

}
