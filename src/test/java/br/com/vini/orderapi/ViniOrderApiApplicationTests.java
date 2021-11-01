package br.com.vini.orderapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.com.vini.orderapi.api.exception.StandardErrorAPI;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;
import br.com.vini.orderapi.api.service.order.protocol.FiscalItemProtocol;
import br.com.vini.orderapi.api.service.order.protocol.OrderIntegrationSimulationProtocol;
import br.com.vini.orderapi.api.service.order.protocol.ResultOrderIntegrationProtocol;
import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;

@TestMethodOrder(value = DisplayName.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ViniOrderApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    @Test
    public void V00001_findProductPageWithoutFilters() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/product", Object.class))
                .hasFieldOrPropertyWithValue("numberOfElements", 13);
    }

    @Test
    public void V00002_findProductPageWithFilterById() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/product?id=1", Object.class))
                .hasFieldOrPropertyWithValue("numberOfElements", 1);
    }

    @Test
    public void V00003_findProductByIdWithSuccess() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/product/1", ProductProtocol.class)).isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("id", "unitValue", "description", "categoty");
    }

    @Test
    public void V00004_findProductByIdWithNotFoundError() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/product/999", StandardErrorAPI.class)).isNotNull()
                .hasFieldOrPropertyWithValue("status", HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void V00005_findCategoryPageWithoutFilters() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/category", Object.class))
                .hasFieldOrPropertyWithValue("numberOfElements", 12);
    }

    @Test
    public void V00006_findCategoryPageWithFilterById() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/category?id=1", Object.class))
                .hasFieldOrPropertyWithValue("numberOfElements", 1);
    }

    @Test
    public void V00007_findCategoryByIdWithSuccess() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/category/1", FiscalCategoryProtocol.class)).isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("id", "description", "taxes");
    }

    @Test
    public void V00008_findCategoryByIdWithNotFoundError() {
        assertThat(this.restTemplate.getForObject(BASE_URL + port + "/category/999", StandardErrorAPI.class)).isNotNull()
                .hasFieldOrPropertyWithValue("status", HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void V00009_simulateOrderWithSuccess() {
        var pipos = this.restTemplate.getForObject(BASE_URL + port + "/product/1", ProductProtocol.class);
        assertThat(pipos).hasFieldOrPropertyWithValue("description", "Pìpos Vitaminado");
        var dorflex = this.restTemplate.getForObject(BASE_URL + port + "/product/12", ProductProtocol.class);
        assertThat(dorflex).hasFieldOrPropertyWithValue("description", "Dorflex");

        OrderIntegrationSimulationProtocol order = new OrderIntegrationSimulationProtocol();

        order.getItems().add(new FiscalItemProtocol(pipos, BigDecimal.valueOf(2)));
        order.getItems().add(new FiscalItemProtocol(dorflex, BigDecimal.valueOf(5)));

        assertThat(this.restTemplate.postForObject(BASE_URL + port + "/order", order, ResultOrderIntegrationProtocol.class))
                .hasNoNullFieldsOrProperties();

    }

    @Test
    public void V00009_simulateOrderWithValidationError() {
        var pipos = this.restTemplate.getForObject(BASE_URL + port + "/product/1", ProductProtocol.class);
        assertThat(pipos).hasFieldOrPropertyWithValue("description", "Pìpos Vitaminado");
        var dorflex = this.restTemplate.getForObject(BASE_URL + port + "/product/12", ProductProtocol.class);
        assertThat(dorflex).hasFieldOrPropertyWithValue("description", "Dorflex");

        OrderIntegrationSimulationProtocol order = new OrderIntegrationSimulationProtocol();

        order.getItems().add(new FiscalItemProtocol(pipos, BigDecimal.valueOf(0)));
        order.getItems().add(new FiscalItemProtocol(dorflex, BigDecimal.valueOf(5)));

        assertThat(this.restTemplate.postForObject(BASE_URL + port + "/order", order, StandardErrorAPI.class)).isNotNull()
                .hasFieldOrPropertyWithValue("status", HttpStatus.UNPROCESSABLE_ENTITY.value());

    }
}
