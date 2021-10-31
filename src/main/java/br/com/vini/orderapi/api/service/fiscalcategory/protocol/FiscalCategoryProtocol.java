package br.com.vini.orderapi.api.service.fiscalcategory.protocol;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.vini.orderapi.api.service.BaseProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe protocolo para tráfego de JSON no controller
 */
@ApiModel(value = "Categoria fiscal")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiscalCategoryProtocol implements BaseProtocol {

    private static final long serialVersionUID = -8344530988273129016L;

    @ApiModelProperty("Identificador único do a categoria")
    @NotNull
    private Long id;

    @NotNull
    @ApiModelProperty("Descrição da categoria")
    private String description;

    @ApiModelProperty("Taxas fiscais")
    @Valid
    private List<ItemTaxProtocol> taxes = new ArrayList<>();

    public FiscalCategoryProtocol() {
        super();
    }

    public FiscalCategoryProtocol(Long id, String description) {
        super();
        this.id = id;
        this.description = description;
        this.taxes = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemTaxProtocol> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<ItemTaxProtocol> taxes) {
        this.taxes = taxes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FiscalCategoryProtocol [id=");
        builder.append(id);
        builder.append(", description=");
        builder.append(description);
        builder.append(", taxes=");
        builder.append(taxes);
        builder.append("]");
        return builder.toString();
    }

}
