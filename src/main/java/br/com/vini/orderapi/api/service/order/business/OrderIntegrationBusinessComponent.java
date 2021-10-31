package br.com.vini.orderapi.api.service.order.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.vini.orderapi.api.exception.HttpException;
import br.com.vini.orderapi.api.service.BaseBusinessComponent;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.ItemTaxProtocol;
import br.com.vini.orderapi.api.service.order.protocol.OrderIntegrationSimulationProtocol;
import br.com.vini.orderapi.api.service.order.protocol.ResultOrderIntegrationProtocol;

/**
 * Componente específico para integração de pedido no sistema
 * 
 * @author viniciosarodrigues
 *
 */
@Component
public class OrderIntegrationBusinessComponent implements BaseBusinessComponent {

    @Autowired
    private Logger logger;

    @Autowired
    public MessageSource messageSource;

    /**
     * Realiza a simulação de integração do pedido calculando valores totais e impostos
     * 
     * @param request Requisição simulação de integração de pedido
     * @return Resultado da simulação
     */
    public ResultOrderIntegrationProtocol simulate(OrderIntegrationSimulationProtocol request) {
        logger.info("Iniciando processo de cálculo do pedido {}", request);

        validateRequest(request);

        ResultOrderIntegrationProtocol simulationResult = new ResultOrderIntegrationProtocol();
        simulationResult.setId(UUID.randomUUID().toString());
        simulationResult.setDate(LocalDateTime.now());
        simulationResult.setCalculatedFiscalItems(request.getItems());
        calculateProductTaxes(simulationResult);
        calculateTotalAndTotalTaxes(simulationResult);
        return simulationResult;
    }

    /**
     * Realiza validações internas afim de garantir segurança
     * 
     * @param request
     */
    private void validateRequest(OrderIntegrationSimulationProtocol request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new HttpException(HttpStatus.UNPROCESSABLE_ENTITY,
                    messageSource.getMessage("error.OrderIntegrationBusinessComponent.calculateProductTaxes.emptyItems", null,
                                             LocaleContextHolder.getLocale()));
        }
    }

    /**
     * Calcula o valor total e total em taxas de todos os itens fiscais juntos
     * 
     * @param simulationResult requisição de simulação
     */
    private void calculateTotalAndTotalTaxes(ResultOrderIntegrationProtocol simulationResult) {
        logger.info("Iniciando processo de cálculo de valor total de impostos do pedido...");
        simulationResult.getFiscalItems().forEach(item -> {
            simulationResult.setTotalTaxes(simulationResult.getTotalTaxes().add(item.getTotalTaxes()));
            simulationResult.setTotal(simulationResult.getTotal().add(item.getTotalValue()));
        });

    }

    /**
     * Calcula o valor de impostos de cada produto e cria um item fiscal no resultado da simulação
     * 
     * @param request Requisição de simulação
     * @param simulationResult Resultado da simulação
     */
    private void calculateProductTaxes(ResultOrderIntegrationProtocol simulationResult) {
        logger.info("Iniciando processo de cálculo de impostos de cada produto...");

        simulationResult.getFiscalItems().forEach(item -> {
            var product = item.getProduct();

            if (item.getQuantity() == null || item.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                throw new HttpException(HttpStatus.UNPROCESSABLE_ENTITY,
                        String.format(messageSource.getMessage("error.OrderIntegrationBusinessComponent.calculateProductTaxes.quantityZero",
                                                               null,
                                                               LocaleContextHolder.getLocale()),
                                      product.getDescription()));
            } else if (product.getUnitValue() == null || product.getUnitValue().compareTo(BigDecimal.ZERO) <= 0) {
                throw new HttpException(HttpStatus.UNPROCESSABLE_ENTITY,
                        String.format(messageSource
                                .getMessage("error.OrderIntegrationBusinessComponent.calculateProductTaxes.unitValueZero",
                                            null,
                                            LocaleContextHolder.getLocale()),
                                      product.getDescription()));
            }
            item.setTotalValue(item.getQuantity().multiply(product.getUnitValue()));

            logger.info("Verificando o produto :: {} | Líquido R$ {} | QTD: {}", product.getDescription(), product.getUnitValue(),
                        item.getQuantity());

            var category = item.getProduct().getCategory();

            if (category != null && category.getTaxes() != null && !category.getTaxes().isEmpty()) {
                var taxes = category.getTaxes();
                logger.info("Impostos encontrados :: {}", taxes);

                var totalPercentual = taxes.stream()
                        .map(ItemTaxProtocol::getPercentual)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                logger.info("Iniciando cálculo de impostos...");
                item.setTotalTaxes(item.getQuantity().multiply(product.getUnitValue()));
                item.setTotalTaxes(item.getTotalTaxes().multiply(totalPercentual.divide(BigDecimal.valueOf(100))));
                item.setTotalValue(item.getTotalValue().add(item.getTotalTaxes()));

                logger.info("Cálculo finalizado! Resultado...\nPercentual total de impostos: {}%\nValor total em impostos: R$ {}\nValor total do item calculado: R$ {}",
                            totalPercentual, item.getTotalTaxes(), item.getTotalValue());
            }
        });

    }

}
