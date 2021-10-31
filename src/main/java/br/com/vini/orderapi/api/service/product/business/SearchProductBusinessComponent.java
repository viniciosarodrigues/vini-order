package br.com.vini.orderapi.api.service.product.business;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.vini.orderapi.api.exception.HttpException;
import br.com.vini.orderapi.api.service.ApiFilter;
import br.com.vini.orderapi.api.service.SearchBaseBusinessComponent;
import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;
import br.com.vini.orderapi.api.service.product.repositories.ProductRepository;
import br.com.vini.orderapi.core.entities.ProductEntity;

@Component
public class SearchProductBusinessComponent extends SearchBaseBusinessComponent<ProductProtocol, Long> {

    @Autowired
    private Logger logger;

    @Autowired
    public MessageSource messageSource;

    @Autowired
    private ProductRepository repository;

    @Override
    public Page<ProductProtocol> getPage(Pageable pageSettings, ApiFilter filters) {
        logger.info("Iniciando processo de paginação de Produtos :: {}", filters);

        return repository.findPage(pageSettings, filters);
    }

    @Override
    public ProductProtocol byId(Long id) {
        logger.info("Iniciando processo de busca de Categoria Fiscal por identificador único :: {}", id);

        ProductEntity entity = repository.findById(id).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND,
                String.format(messageSource.getMessage("error.fiscalcategory.business.SearchProductBusinessComponent.not-found", null,
                                                       LocaleContextHolder.getLocale()),
                              id)));
        logger.info("Produto encontrado :: {}", entity);

        return entity.getProtocol();
    }

}
