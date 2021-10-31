package br.com.vini.orderapi.api.service.order.protocol;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Item fiscal calculado do pedido")
public class CalculatedFiscalItemProtocol extends FiscalItemProtocol {

    private static final long serialVersionUID = -1675692446555121922L;

    @ApiModelProperty("Produto")
    @NotNull
    private ProductProtocol product;
    @ApiModelProperty("Quantidade do produto")
    @NotNull
    private BigDecimal quantity;
    @ApiModelProperty("Valor total calculado")
    private BigDecimal totalValue = BigDecimal.ZERO;
    @ApiModelProperty("Valor total de impostos calculados")
    private BigDecimal totalTaxes = BigDecimal.ZERO;

    public ProductProtocol getProduct() {
        return product;
    }

    public void setProduct(ProductProtocol product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FiscalItemProtocol [product=");
        builder.append(product);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", totalValue=");
        builder.append(totalValue);
        builder.append(", totalTaxes=");
        builder.append(totalTaxes);
        builder.append("]");
        return builder.toString();
    }

}
