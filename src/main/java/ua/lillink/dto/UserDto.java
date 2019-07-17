package ua.lillink.dto;

import lombok.Data;
import ua.lillink.validation.PasswordMatches;
import ua.lillink.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
@Data
public class UserDto {

    @NotNull
    @NotEmpty
    @ValidEmail
    private String login;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;

    @NotNull
    private String matchingPassword;
}
