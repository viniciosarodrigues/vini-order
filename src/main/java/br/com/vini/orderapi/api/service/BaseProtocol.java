package br.com.vini.orderapi.api.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe base para criação de interfaces de comunicação
 * 
 * @author viniciosarodrigues
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface BaseProtocol extends Serializable {

}
