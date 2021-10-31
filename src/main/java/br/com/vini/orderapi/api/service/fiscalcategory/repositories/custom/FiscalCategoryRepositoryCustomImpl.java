package br.com.vini.orderapi.api.service.fiscalcategory.repositories.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import br.com.vini.orderapi.api.exception.HttpException;
import br.com.vini.orderapi.api.service.ApiFilter;
import br.com.vini.orderapi.api.service.GenericPageableRepository;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;
import br.com.vini.orderapi.core.entities.FiscalCategoryEntity;

/**
 * Repositório customizado para paginação
 * 
 * @author viniciosarodrigues
 *
 */
public class FiscalCategoryRepositoryCustomImpl extends GenericPageableRepository<FiscalCategoryEntity, FiscalCategoryProtocol>
        implements FiscalCategoryRepositoryCustom {

    @Override
    public Page<FiscalCategoryProtocol> findPage(Pageable pageSettings, ApiFilter filters) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<FiscalCategoryProtocol> criteria = builder.createQuery(FiscalCategoryProtocol.class);
        Root<FiscalCategoryEntity> root = criteria.from(FiscalCategoryEntity.class);

        criteria.select(builder.construct(FiscalCategoryProtocol.class,
                                          root.get("id"),
                                          root.get("description")));

        return getPage(filters, pageSettings, builder, criteria, root);
    }

    @Override
    protected Predicate[] createRestrictions(ApiFilter customFilters, CriteriaBuilder builder, Root<FiscalCategoryEntity> root) {
        if (!(customFilters instanceof FiscalCategoryFilter filter)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "O filtro enviado não é um filtro de categoria fiscal");
        }

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getId() != null) {
            predicates.add(builder.equal(root.get("id"), filter.getId()));
        }
        predicates.add(builder.like(root.get("description"), filter.getDescription()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
