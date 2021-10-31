package br.com.vini.orderapi.core.entities.converters;

import javax.persistence.AttributeConverter;

/**
 * Conversor de booleano para colunas S ou N (Sim ou Não)
 * 
 * @author viniciosarodrigues
 *
 */
public class BooleanConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        if (attribute != null) {
            if (attribute) {
                return 'S';
            } else {
                return 'N';
            }

        }
        return 'N';
    }

    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        if (dbData != null) {
            return dbData.equals('S');
        }
        return false;
    }

}