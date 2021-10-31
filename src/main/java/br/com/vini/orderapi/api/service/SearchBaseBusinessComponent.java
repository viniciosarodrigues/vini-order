package br.com.vini.orderapi.api.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Componente de negócio base para criação de operações de consulta
 * 
 * @author viniciosarodrigues
 *
 * @param <T> Tipo da classe base de protocolos
 * @param <K> Tipo da chave primária da entidade referente
 * 
 * @see BaseProtocol
 */
@Component
public abstract class SearchBaseBusinessComponent<T extends BaseProtocol, K extends Serializable> implements BaseBusinessComponent {

    /**
     * Operação de busca paginada com filtros
     * 
     * @param pageSettings Configurações de paginação
     * @param filter Filtros de busca
     * @return Página com informações da entidade protocolada
     */
    public abstract Page<T> getPage(Pageable pageSettings, ApiFilter filters);

    /**
     * Operação de busca por identificador único da entidade
     * 
     * @param id Identificador único da entidade
     * @return Entidade encontrada
     */
    public abstract T byId(K id);
}
