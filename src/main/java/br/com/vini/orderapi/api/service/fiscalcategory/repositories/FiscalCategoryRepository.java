package br.com.vini.orderapi.api.service.fiscalcategory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vini.orderapi.api.service.fiscalcategory.repositories.custom.FiscalCategoryRepositoryCustom;
import br.com.vini.orderapi.core.entities.FiscalCategoryEntity;

/**
 * <p>
 * Repositório base para manipulação de entidades do tipo Categoria Fiscal no banco de dados.
 * </p>
 * <small>Herde esta interface para customizações mais avançadas. Nao criar implementações default nesta interface.</small>
 * 
 * 
 * @author viniciosarodrigues
 * 
 * @see FiscalCategoryEntity
 *
 */
@Repository
public interface FiscalCategoryRepository extends JpaRepository<FiscalCategoryEntity, Long>, FiscalCategoryRepositoryCustom {

}
