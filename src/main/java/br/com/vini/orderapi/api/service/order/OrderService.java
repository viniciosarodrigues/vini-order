package br.com.vini.orderapi.api.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vini.orderapi.api.service.BaseService;
import br.com.vini.orderapi.api.service.order.business.OrderIntegrationBusinessComponent;
import br.com.vini.orderapi.api.service.order.protocol.OrderIntegrationSimulationProtocol;
import br.com.vini.orderapi.api.service.order.protocol.ResultOrderIntegrationProtocol;

/**
 * Componente de serviços para opeação de integração de pedidos
 * 
 * @author viniciosarodrigues
 *
 */
@Service
public class OrderService implements BaseService {

    @Autowired
    private OrderIntegrationBusinessComponent orderIntegrationBusinessComponent;

    /**
     * Realiza a integração do pedido em questão calculando valores totais e impostos
     * 
     * @param request Requisição de integração de pedido
     * @return Pedido integrado
     */
    public ResultOrderIntegrationProtocol simulate(OrderIntegrationSimulationProtocol request) {
        return orderIntegrationBusinessComponent.simulate(request);
    }
}
