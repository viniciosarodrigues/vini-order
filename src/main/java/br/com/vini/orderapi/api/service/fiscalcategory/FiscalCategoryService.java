package br.com.vini.orderapi.api.service.fiscalcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vini.orderapi.api.exception.HttpException;
import br.com.vini.orderapi.api.service.ApiFilter;
import br.com.vini.orderapi.api.service.BaseOperationsWithProtocolService;
import br.com.vini.orderapi.api.service.fiscalcategory.business.SearchFiscalCategoryBusinessComponent;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;

/**
 * Camada de serviço para operações (CRUD) em Categoria Fiscal
 * 
 * @author viniciosarodrigues
 * 
 * @see BaseOperationsWithProtocolService
 * @see FiscalCategoryProtocol
 *
 */
@Service
public class FiscalCategoryService implements BaseOperationsWithProtocolService<FiscalCategoryProtocol, Long> {

    @Autowired
    private SearchFiscalCategoryBusinessComponent searchFiscalCategoryBusinessComponent;

    @Override
    public Page<FiscalCategoryProtocol> getPage(Pageable pageSettings, ApiFilter filter) {
        return searchFiscalCategoryBusinessComponent.getPage(pageSettings, filter);
    }

    @Override
    public FiscalCategoryProtocol findById(Long id) {
        return searchFiscalCategoryBusinessComponent.byId(id);
    }

    @Override
    public FiscalCategoryProtocol update(FiscalCategoryProtocol entity) {
        throw new HttpException(HttpStatus.NOT_IMPLEMENTED, "Método não implementado");
    }

    @Override
    public void deleteById(Long id) {
        throw new HttpException(HttpStatus.NOT_IMPLEMENTED, "Método não implementado");
    }

    @Override
    public FiscalCategoryProtocol insert(FiscalCategoryProtocol entity) {
        throw new HttpException(HttpStatus.NOT_IMPLEMENTED, "Método não implementado");
    }

}
