package br.com.vini.orderapi.api.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Condiguração padrão do Swagger
 * 
 * @author viniciosarodrigues
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m201 = simpleMessage(201, "Recurso criado");
    private final ResponseMessage m204put = simpleMessage(204, "Atualização ok");
    private final ResponseMessage m204del = simpleMessage(204, "Deleção ok");
    private final ResponseMessage m400 = simpleMessage(400, "Requisição mal formada");
    private final ResponseMessage m401 = simpleMessage(401, "Não autenticado");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
    private final ResponseMessage m409 = simpleMessage(409, "Requisição conflitante");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro não esperado");

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m400, m401, m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m400, m401, m403, m409, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m400, m401, m403, m404, m409, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m400, m401, m403, m404, m409, m500))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Vini API",
                "Este é o catálogo de serviços RESTFUL de Vinícios Rodrigues, para saber mais sobre acesse: [Vini Order - API](https://vini-order-api.herokuapp.com/swagger-ui.html).",
                "STORE-REST", "#", null, "Licensa da API", "#", Collections.emptyList());
    }

}