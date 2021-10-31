package br.com.vini.orderapi.api.service.order.protocol;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import br.com.vini.orderapi.api.service.BaseProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Objeto de integração de pedidos no sistema
 * 
 * @author viniciosarodrigues
 *
 */
@ApiModel(value = "Simulação de integração de pedido")
public class OrderIntegrationSimulationProtocol implements BaseProtocol {

    private static final long serialVersionUID = 7047735568666489059L;

    @ApiModelProperty("Items do pedido")
    @Valid
    private List<FiscalItemProtocol> items = new ArrayList<>();

    public List<FiscalItemProtocol> getItems() {
        return items;
    }

    public void setItems(List<FiscalItemProtocol> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderIntegrationSimulationProtocol [items=");
        builder.append(items);
        builder.append("]");
        return builder.toString();
    }

}
