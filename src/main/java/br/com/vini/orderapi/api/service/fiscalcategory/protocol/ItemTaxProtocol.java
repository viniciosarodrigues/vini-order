package br.com.vini.orderapi.api.service.fiscalcategory.protocol;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.vini.orderapi.api.service.BaseProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Protocolo para itens fiscais
 * 
 * @author viniciosarodrigues
 *
 */
@ApiModel("Taxa fiscal")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemTaxProtocol implements BaseProtocol {

    private static final long serialVersionUID = 266836851041856896L;

    @ApiModelProperty("Identificador único da Taxa Fiscal")
    @NotNull
    private Long id;

    @ApiModelProperty("Nome da Taxa Fiscal (imposto)")
    @NotNull
    private String name;

    @ApiModelProperty("Percentual da Taxa Fiscal")
    @NotNull
    private BigDecimal percentual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FiscalItemProtocol [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", percentual=");
        builder.append(percentual);
        builder.append("]");
        return builder.toString();
    }

}
