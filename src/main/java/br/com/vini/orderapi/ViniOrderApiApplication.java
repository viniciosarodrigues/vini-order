package br.com.vini.orderapi;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class ViniOrderApiApplication {

    public static final LocalDateTime SERVER_STARTUP_DATE_TIME = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public static final String ENCODING_APPLICATION = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(ViniOrderApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ViniOrderApiApplication.class, args);
    }

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        logger.info("======== Data/Hora: " + SERVER_STARTUP_DATE_TIME.format(DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss"))
                + " ==========");
        logger.info("==================================================");

        logger.info("Configurando Time Zone para {}", TimeZone.getDefault().getDisplayName());

        logger.info("==================================================");
        logger.info("Inicializando Bundles de mensagens da aplicação! ");

        messageSource.addBasenames("classpath:app-messages/common-messages",
                                   "classpath:app-messages/error-messages");

        messageSource.setCacheSeconds(86400);
        messageSource.setDefaultEncoding(ENCODING_APPLICATION);

        logger.info(messageSource.getMessage("common.message.test", null, "Bundle Common não carregado!", LocaleContextHolder.getLocale()));
        logger.info(messageSource.getMessage("error.message.test", null, "Bundle Error não carregado!", LocaleContextHolder.getLocale()));

        logger.info("==================================================");
        logger.info("Configuração do tempo de cache de mensagens: 24h");
        logger.info("==================================================");
        logger.info("Encoding padrão das mensagens da aplicação: {}", ENCODING_APPLICATION);
        logger.info("==================================================");
        logger.info("==================================================");
        logger.info("Locale atual: {}", LocaleContextHolder.getLocale());
        logger.info("==================================================");

        return messageSource;
    }

}
