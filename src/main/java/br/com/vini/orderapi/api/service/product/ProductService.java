package br.com.vini.orderapi.api.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vini.orderapi.api.exception.HttpException;
import br.com.vini.orderapi.api.service.ApiFilter;
import br.com.vini.orderapi.api.service.BaseOperationsWithProtocolService;
import br.com.vini.orderapi.api.service.product.business.SearchProductBusinessComponent;
import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;
import br.com.vini.orderapi.core.entities.ProductEntity;

/**
 * Camada de serviços para operações cadatras com produtos
 * 
 * @author viniciosarodrigues
 * 
 * @see ProductEntity
 * @see ProductProtocol
 *
 */
@Service
public class ProductService implements BaseOperationsWithProtocolService<ProductProtocol, Long> {

    @Autowired
    private SearchProductBusinessComponent searchProductBusinessComponent;

    @Override
    public Page<ProductProtocol> getPage(Pageable pageSettings, ApiFilter filter) {
        return searchProductBusinessComponent.getPage(pageSettings, filter);
    }

    @Override
    public ProductProtocol findById(Long id) {
        return searchProductBusinessComponent.byId(id);
    }

    @Override
    public ProductProtocol update(ProductProtocol entity) {
        throw new HttpException(HttpStatus.NOT_IMPLEMENTED, "Método não implementado");
    }

    @Override
    public void deleteById(Long id) {
        throw new HttpException(HttpStatus.NOT_IMPLEMENTED, "Método não implementado");
    }

    @Override
    public ProductProtocol insert(ProductProtocol entity) {
        throw new HttpException(HttpStatus.NOT_IMPLEMENTED, "Método não implementado");
    }
}