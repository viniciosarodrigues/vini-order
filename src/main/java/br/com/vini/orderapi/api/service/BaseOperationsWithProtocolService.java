package br.com.vini.orderapi.api.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Classe base para a criação de implementações de serviços com operações comuns (CRUD)
 * 
 * @author viniciosarodrigues
 *
 * @param <T> Tipo da classe de protocolo
 * @param <K> Tipo da Chave
 */
public interface BaseOperationsWithProtocolService<T extends BaseProtocol, K extends Serializable> extends BaseService {

    /**
     * Operação de busca paginada com filtros
     * 
     * @param pageSettings Configurações de paginação
     * @param filter Filtros de busca
     * @return Página com informações da entidade protocolada
     */
    public Page<T> getPage(Pageable pageSettings, ApiFilter filter);

    /**
     * Operação de busca por identificador único da entidade
     * 
     * @param id Identificador único da entidade
     * @return Entidade encontrada
     */
    public T findById(K id);

    /**
     * Operação de atualização de entidade
     * 
     * @param entity Entidade à ser atualizada
     * @return Entidade atualizada
     */
    public T update(T entity);

    /**
     * Operação de exclusão de entidade por identificador único
     * 
     * @param id Identificador único da entidade
     */
    public void deleteById(K id);

    /**
     * Operação de criação cadastra da entidade
     * 
     * @param entity Entidade à ser cadastrada
     * @return Entidade cadastrada
     */
    public T insert(T entity);

}
