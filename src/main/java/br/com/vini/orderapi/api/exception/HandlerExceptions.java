package br.com.vini.orderapi.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Tratador de erros da API. Mapeado apenas os possíveis erros.
 * 
 * @author viniciosarodrigues
 *
 */
@ControllerAdvice
public class HandlerExceptions {

    private Logger logger = LoggerFactory.getLogger(HandlerExceptions.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * Captura erros não esperados
     * 
     * @param e Erro não tratado
     * @param req Requisição
     * @return Erro padrão da API
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<StandardErrorAPI> objetoNaoEncontrado(Throwable e, HttpServletRequest req) {
        logger.error(handlerErrorByStatus(HttpStatus.INTERNAL_SERVER_ERROR), e);
        StandardErrorAPI err = new StandardErrorAPI(System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), handlerErrorByStatus(HttpStatus.INTERNAL_SERVER_ERROR), Arrays.asList(),
                e.getMessage(),
                req.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    /**
     * Captura erros padrões da API
     * 
     * @param e Erro HTTP geral da da API
     * @param req Requisição
     * @return Erro padrão da API
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<StandardErrorAPI> httpException(HttpException e, HttpServletRequest req) {
        logger.error(handlerErrorByStatus(e.getStatus()), e);
        StandardErrorAPI err = new StandardErrorAPI(System.currentTimeMillis(), e.getStatus().value(),
                handlerErrorByStatus(e.getStatus()), Arrays.asList(), e.getMessage(), req.getRequestURI());

        return ResponseEntity.status(e.getStatus()).body(err);
    }

    /**
     * Retorna uma descrição curta de um erro baseado no status http
     * 
     * @param status Código HTTP
     * @return Mensagem tratada
     */
    private String handlerErrorByStatus(HttpStatus status) {
        switch (status) {
            case BAD_REQUEST:
                return messageSource.getMessage("error.httpStatus.bad-request", null, LocaleContextHolder.getLocale());
            case NOT_FOUND:
                return messageSource.getMessage("error.httpStatus.not-found", null, LocaleContextHolder.getLocale());
            case REQUEST_TIMEOUT:
                return messageSource.getMessage("error.httpStatus.request", null, LocaleContextHolder.getLocale());
            case CONFLICT:
                return messageSource.getMessage("error.httpStatus.conflict", null, LocaleContextHolder.getLocale());
            case UNSUPPORTED_MEDIA_TYPE:
                return messageSource.getMessage("error.httpStatus.unsupported-media-type", null, LocaleContextHolder.getLocale());
            case UNPROCESSABLE_ENTITY:
                return messageSource.getMessage("error.httpStatus.unprocessable-entity", null, LocaleContextHolder.getLocale());
            default:
                return messageSource.getMessage("error.httpStatus.internal-server-error", null, LocaleContextHolder.getLocale());
        }
    }

    /**
     * Trata erros de validações
     * 
     * @param e Exception de validações
     * @param req Requisição
     * @return Erro padrão da API com detalhes da exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorAPI> methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                            HttpServletRequest req) {
        StandardErrorAPI err = new StandardErrorAPI(System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                messageSource.getMessage("error.handlerException.methodArgumentNotValidException.title", null,
                                         LocaleContextHolder.getLocale()),
                getValidations(e),
                messageSource.getMessage("error.handlerException.methodArgumentNotValidException.description", null,
                                         LocaleContextHolder.getLocale()),
                req.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    /**
     * Trata erros de violação de integridade com o banco de dados
     * 
     * @param e Exception de violação de integridade
     * @param req Requisição
     * @return Erro padrão da API com detalhes da exception
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardErrorAPI> dataIntegrityViolationException(DataIntegrityViolationException e,
                                                                            HttpServletRequest req) {
        StandardErrorAPI err = new StandardErrorAPI(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                messageSource.getMessage("error.handlerException.dataIntegrityViolationException.title", null,
                                         LocaleContextHolder.getLocale()),
                Arrays.asList(),
                messageSource.getMessage("error.handlerException.dataIntegrityViolationException.description", null,
                                         LocaleContextHolder.getLocale()),
                req.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    /**
     * Captura os erros de validações e converte em uma lista
     * 
     * @param e Exception de validação
     * @return Lista de erros de validações
     */
    private List<ErrorItem> getValidations(MethodArgumentNotValidException e) {
        List<ErrorItem> errors = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(new ErrorItem(error.getField(), error.getDefaultMessage()));
        }
        return errors;
    }

}