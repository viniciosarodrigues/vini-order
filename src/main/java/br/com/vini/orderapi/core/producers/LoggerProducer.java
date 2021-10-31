package br.com.vini.orderapi.core.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Produtor de instância para Logger
 * 
 * @author viniciosarodrigues
 *
 */
@Configuration
public class LoggerProducer {

    @Bean
    public Logger getLogger(InjectionPoint p) {
        return LoggerFactory.getLogger(p.getClass().getCanonicalName());
    }
}