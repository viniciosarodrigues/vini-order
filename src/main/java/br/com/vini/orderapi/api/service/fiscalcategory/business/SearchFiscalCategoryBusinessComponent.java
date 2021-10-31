package br.com.vini.orderapi.api.service.fiscalcategory.business;

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
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;
import br.com.vini.orderapi.api.service.fiscalcategory.repositories.FiscalCategoryRepository;
import br.com.vini.orderapi.core.entities.FiscalCategoryEntity;

/**
 * Componente de busca para Categoria fiscal
 * 
 * @author viniciosarodrigues
 * 
 * @see SearchBaseBusinessComponent
 * @see FiscalCategoryProtocol
 *
 */
@Component
public class SearchFiscalCategoryBusinessComponent extends SearchBaseBusinessComponent<FiscalCategoryProtocol, Long> {

    @Autowired
    private Logger logger;

    @Autowired
    public MessageSource messageSource;

    @Autowired
    private FiscalCategoryRepository repository;

    @Override
    public Page<FiscalCategoryProtocol> getPage(Pageable pageSettings, ApiFilter filters) {
        logger.info("Iniciando processo de paginação de Categorias fiscais :: {}", filters);

        return repository.findPage(pageSettings, filters);
    }

    @Override
    public FiscalCategoryProtocol byId(Long id) {
        logger.info("Iniciando processo de busca de Categoria Fiscal por identificador único :: {}", id);

        FiscalCategoryEntity entity = repository.findById(id).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND,
                String.format(messageSource.getMessage("error.fiscalcategory.business.SearchFiscalCategoryBusinessComponent.not-found",
                                                       null,
                                                       LocaleContextHolder.getLocale()),
                              id)));

        logger.info("Categoria fiscal encontrada :: {}", entity);

        return entity.getProtocol();
    }

}
