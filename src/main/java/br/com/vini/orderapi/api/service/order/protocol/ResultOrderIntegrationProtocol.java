package br.com.vini.orderapi.api.service.order.protocol;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.vini.orderapi.api.service.BaseProtocol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Protocolo de resultado de simulação de integração de pedido
 * 
 * @author viniciosarodrigues
 *
 */
@ApiModel(value = "Resultado de simulação de pedido")
public class ResultOrderIntegrationProtocol implements BaseProtocol {

    private static final long serialVersionUID = 8972181328794255109L;

    @ApiModelProperty("Identificador único do pedido")
    private String id;

    @ApiModelProperty("Data de criação do pedido")
    private LocalDateTime date;

    @ApiModelProperty("Itens fiscais do pedido")
    private List<CalculatedFiscalItemProtocol> fiscalItems = new ArrayList<>();

    @ApiModelProperty("Valor total calculado")
    private BigDecimal total = BigDecimal.ZERO;

    @ApiModelProperty("Valor total de impostos calculados")
    private BigDecimal totalTaxes = BigDecimal.ZERO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<CalculatedFiscalItemProtocol> getFiscalItems() {
        return fiscalItems;
    }

    public void setFiscalItems(List<CalculatedFiscalItemProtocol> fiscalItems) {
        this.fiscalItems = fiscalItems;
    }

    public void setCalculatedFiscalItems(List<FiscalItemProtocol> fiscalItems) {
        fiscalItems.forEach(item -> {
            CalculatedFiscalItemProtocol calcItem = new CalculatedFiscalItemProtocol();
            BeanUtils.copyProperties(item, calcItem);
            this.getFiscalItems().add(calcItem);
        });
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        builder.append("ResultOrderIntegrationProtocol [id=");
        builder.append(id);
        builder.append(", date=");
        builder.append(date);
        builder.append(", fiscalItems=");
        builder.append(fiscalItems);
        builder.append(", total=");
        builder.append(total);
        builder.append(", totalTaxes=");
        builder.append(totalTaxes);
        builder.append("]");
        return builder.toString();
    }

}
