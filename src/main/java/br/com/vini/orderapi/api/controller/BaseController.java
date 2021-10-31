package br.com.vini.orderapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.vini.orderapi.api.service.BaseService;

/**
 * Controller base para a criação de outros controllers
 * 
 * @author viniciosarodrigues
 *
 * @param <S> Classe de serviço
 */
public class BaseController<S extends BaseService> {

    @Autowired
    protected S service;
}
