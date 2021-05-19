package br.com.leite.aquilla.instagramapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@AllArgsConstructor
@Getter
@ToString
public enum GenderEnum {
    MALE('M', "Male"), FEMALE('F', "Female"), OTHERS('O', "Others");

    private final Character acronym;
    private final String describe;

    public static GenderEnum valueOf(Character code) {
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getAcronym().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid GenderEnum");
    }

    @Converter
    public static class GenderEnumConverter implements AttributeConverter<GenderEnum, Character> {
        @Override
        public Character convertToDatabaseColumn(GenderEnum attribute) {
            return attribute.getAcronym();
        }

        @Override
        public GenderEnum convertToEntityAttribute(Character dbData) {
            return GenderEnum.valueOf(dbData);
        }
    }
}
