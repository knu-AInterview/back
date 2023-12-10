package knu.ainterview.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import knu.ainterview.controller.dto.QnA;

import javax.persistence.AttributeConverter;
import java.util.List;

public class QnAListConverter implements AttributeConverter<List<QnA>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<QnA> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QnA> convertToEntityAttribute(String dbData) {
        TypeReference<List<QnA>> typeReference = new TypeReference<>() {};
        try {
            return objectMapper.readValue(dbData, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
