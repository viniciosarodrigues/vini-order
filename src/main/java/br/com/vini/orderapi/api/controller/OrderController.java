package br.com.vini.orderapi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vini.orderapi.api.service.order.OrderService;
import br.com.vini.orderapi.api.service.order.protocol.OrderIntegrationSimulationProtocol;
import br.com.vini.orderapi.api.service.order.protocol.ResultOrderIntegrationProtocol;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"Pedido"})
@RestController
@RequestMapping(value = "/order")
public class OrderController extends BaseController<OrderService> {

    @ApiOperation(value = "Simula venda")
    @PutMapping
    public ResponseEntity<ResultOrderIntegrationProtocol> simulate(@RequestBody @Validated @ApiParam(name = "JSON de requisição para simulação de venda", value = "Operação de simulação de venda. Retorna um objeto pronto para a integração de venda", required = true) OrderIntegrationSimulationProtocol request) {
        return ResponseEntity.ok(service.simulate(request));
    }

}
