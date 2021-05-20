package br.com.leite.aquilla.instagramapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@AllArgsConstructor
@Getter
@ToString
public enum AccountTypeEnum {
    BUSINESS('B', "Business"), STANDARD('S', "Standard");

    private final Character acronym;
    private final String describe;

    public static AccountTypeEnum valueOf(final Character code) {
        for (AccountTypeEnum value : AccountTypeEnum.values()) {
            if (value.getAcronym().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid AccountTypeEnum");
    }

    @Converter
    public static class AccountTypeEnumConverter implements AttributeConverter<AccountTypeEnum, Character> {
        @Override
        public Character convertToDatabaseColumn(final AccountTypeEnum attribute) {
            return attribute.getAcronym();
        }

        @Override
        public AccountTypeEnum convertToEntityAttribute(final Character dbData) {
            return AccountTypeEnum.valueOf(dbData);
        }
    }
}
