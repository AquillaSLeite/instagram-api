package br.com.leite.aquilla.instagramapi.model.dto;

import br.com.leite.aquilla.instagramapi.enums.AccountTypeEnum;
import br.com.leite.aquilla.instagramapi.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Min(value = 1L)
    private Long id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Size(min = 1, max = 50)
    private String username;
    @NotNull
    @Size(min = 1, max = 50)
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "([0-9]{13})")
    private String phone;
    @NotNull
    private GenderEnum gender;
    @NotNull
    private AccountTypeEnum accountType;
}
