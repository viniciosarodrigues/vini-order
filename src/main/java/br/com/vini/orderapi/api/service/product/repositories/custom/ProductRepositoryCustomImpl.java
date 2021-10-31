package br.com.vini.orderapi.api.service.product.repositories.custom;

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
import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;
import br.com.vini.orderapi.core.entities.ProductEntity;

/**
 * Repositório customizado para paginação
 * 
 * @author viniciosarodrigues
 *
 */
public class ProductRepositoryCustomImpl extends GenericPageableRepository<ProductEntity, ProductProtocol>
        implements ProductRepositoryCustom {

    @Override
    public Page<ProductProtocol> findPage(Pageable pageSettings, ApiFilter filters) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductProtocol> criteria = builder.createQuery(ProductProtocol.class);
        Root<ProductEntity> root = criteria.from(ProductEntity.class);

        criteria.select(builder.construct(ProductProtocol.class,
                                          root.get("id"),
                                          root.get("unitValue"),
                                          root.get("description"),
                                          root.get("fiscalCategory").get("id"),
                                          root.get("fiscalCategory").get("description")));

        return getPage(filters, pageSettings, builder, criteria, root);
    }

    @Override
    protected Predicate[] createRestrictions(ApiFilter customFilters, CriteriaBuilder builder, Root<ProductEntity> root) {
        if (!(customFilters instanceof ProductFilter filter)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "O filtro enviado não é um filtro de produto");
        }

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getId() != null) {
            predicates.add(builder.equal(root.get("id"), filter.getId()));
        }
        predicates.add(builder.like(root.get("description"), filter.getDescription()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
