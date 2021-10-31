package br.com.vini.orderapi.api.service.order.protocol;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.vini.orderapi.api.service.BaseProtocol;
import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Item fiscal do pedido")
public class FiscalItemProtocol implements BaseProtocol {

    private static final long serialVersionUID = -1675692446555121922L;

    @ApiModelProperty("Produto")
    @NotNull
    @Valid
    private ProductProtocol product;
    @ApiModelProperty("Quantidade do produto")
    @NotNull
    private BigDecimal quantity;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FiscalItemProtocol [product=");
        builder.append(product);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append("]");
        return builder.toString();
    }

}
