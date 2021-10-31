package br.com.vini.orderapi.api.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Classe gerénica para implementação de repositórios customizados com paginação
 * 
 * @author viniciosarodrigues
 *
 * @param <T> Tipo da entidade principal
 * @param <P> Tipo da classe protocolo
 */
public abstract class GenericPageableRepository<T extends Serializable, P extends BaseProtocol> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    private Class<T> getPersistentClass(final Class<?> clazz) {
        final Type type = clazz.getGenericSuperclass();

        ParameterizedType paramType;

        if (type instanceof ParameterizedType) {
            paramType = (ParameterizedType) type;
        } else {
            paramType = (ParameterizedType) ((Class<T>) type).getGenericSuperclass();
        }
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    /**
     * Inicializa a entidade de persistência
     * 
     * @return
     */
    protected Class<T> getDelegateClass() {
        if (this.persistentClass == null) {
            this.persistentClass = this.getPersistentClass(this.getClass());
        }
        return this.persistentClass;
    }

    /**
     * Construtor privado Inicializa a estrutura básica do JPA
     */
    protected GenericPageableRepository() {
        this.getDelegateClass();
    }

    /**
     * Cria a query de pesquisa
     * 
     * @param criteria
     * @param pageSettings
     * @return
     */
    protected TypedQuery<P> createQuery(CriteriaQuery<P> criteria, Pageable pageSettings) {
        TypedQuery<P> query = entityManager.createQuery(criteria);
        createPaginationRestrictions(query, pageSettings);
        return query;
    }

    /**
     * Busca o total de elementos da pesquisa
     * 
     * @param filters
     * @return
     */
    protected Long getTotalElements(ApiFilter filters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(persistentClass);

        Predicate[] predicates = createRestrictions(filters, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * Devolve a página solicitada à partir dos filtros
     * 
     * @param filters
     * @param pageSettings
     * @return
     */
    protected Page<P> getPage(ApiFilter filters, Pageable pageSettings, CriteriaBuilder builder, CriteriaQuery<P> criteria,
                              Root<T> root) {

        criteria.where(createRestrictions(filters, builder, root));
        TypedQuery<P> query = createQuery(criteria, pageSettings);

        return new PageImpl<>(query.getResultList(), pageSettings, getTotalElements(filters));
    }

    /**
     * Cria restrições de paginação
     * 
     * @param query
     * @param pageSettings
     */
    protected void createPaginationRestrictions(TypedQuery<P> query, Pageable pageSettings) {
        query.setFirstResult(pageSettings.getPageNumber() * pageSettings.getPageSize());
        query.setMaxResults(pageSettings.getPageSize());

    }

    /**
     * Método usado para criar restrições de consulta com filtros
     * 
     * @param customFilters
     * @param builder
     * @param root
     * @return
     */
    protected abstract Predicate[] createRestrictions(ApiFilter customFilters, CriteriaBuilder builder, Root<T> root);

}
