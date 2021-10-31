package br.com.vini.orderapi.api.service.product.protocol;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.vini.orderapi.api.service.BaseProtocol;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Protocolo de entrada e saída que representa um Produto
 * 
 * @author viniciosarodrigues
 *
 */
@ApiModel(value = "Produto")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductProtocol implements BaseProtocol {

    private static final long serialVersionUID = -2163017959464681042L;

    @ApiModelProperty("Identificador único do produto")
    @NotNull
    private Long id;

    @ApiModelProperty("Valor unitário")
    @NotNull
    private BigDecimal unitValue;

    @ApiModelProperty("Descrição do produto")
    @NotNull
    @NotEmpty
    private String description;

    @ApiModelProperty("Categoria fiscal do produto")
    @Valid
    private FiscalCategoryProtocol category;

    public ProductProtocol() {
    }

    public ProductProtocol(Long id, BigDecimal unitValue, String description, Long categoryId, String categoryDescription) {
        super();
        this.id = id;
        this.unitValue = unitValue;
        this.description = description;
        if (categoryId != null && categoryDescription != null) {
            this.category = new FiscalCategoryProtocol(categoryId, categoryDescription);
            this.category.setTaxes(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FiscalCategoryProtocol getCategory() {
        return category;
    }

    public void setCategory(FiscalCategoryProtocol category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductProtocol [id=");
        builder.append(id);
        builder.append(", unitValue=");
        builder.append(unitValue);
        builder.append(", description=");
        builder.append(description);
        builder.append(", category=");
        builder.append(category);
        builder.append("]");
        return builder.toString();
    }

}
