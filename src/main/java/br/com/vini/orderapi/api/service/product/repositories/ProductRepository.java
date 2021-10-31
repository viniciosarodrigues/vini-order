package br.com.vini.orderapi.api.service.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vini.orderapi.api.service.product.repositories.custom.ProductRepositoryCustom;
import br.com.vini.orderapi.core.entities.FiscalCategoryEntity;
import br.com.vini.orderapi.core.entities.ProductEntity;

/**
 * <p>
 * Repositório base para manipulação de entidades do tipo Produto no banco de dados.
 * </p>
 * <small>Herde esta interface para customizações mais avançadas. Nao criar implementações default nesta interface.</small>
 * 
 * 
 * @author viniciosarodrigues
 * 
 * @see ProductEntity
 * @see FiscalCategoryEntity
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom {

}
