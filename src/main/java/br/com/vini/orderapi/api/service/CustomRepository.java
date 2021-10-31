package br.com.vini.orderapi.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface padrão para customização de repositórios
 * 
 * @author viniciosarodrigues
 *
 * @param <P> Tipo da classe protocolo de transferência
 */
public interface CustomRepository<P extends BaseProtocol> {

    /**
     * Busca paginada com classe de protocolo
     * 
     * @param pageSettings Configurações de paginação
     * @param filters Filtros de busca
     * @return Página filtrada e protocolada
     */
    Page<P> findPage(Pageable pageSettings, ApiFilter filters);
}
