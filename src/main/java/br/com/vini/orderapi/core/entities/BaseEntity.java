package br.com.vini.orderapi.core.entities;

import java.io.Serializable;

import br.com.vini.orderapi.api.service.BaseProtocol;

/**
 * Classe base para criação de entidades relacionais
 * 
 * @author viniciosarodrigues
 *
 * @param <K> Tipo da chave
 */
public abstract class BaseEntity<K extends Serializable, P extends BaseProtocol> implements Serializable {

    private static final long serialVersionUID = 8618490939235842091L;

    /**
     * Captura a chave primária da entidade
     * 
     * @return Valor da chave primária
     */
    public abstract K getId();

    /**
     * Métoro de converção de entidade para protocolo
     * 
     * @return Protocolo da entidade
     */
    public abstract P getProtocol();
}
