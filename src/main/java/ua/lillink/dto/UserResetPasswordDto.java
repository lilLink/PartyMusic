package ua.lillink.dto;

import lombok.Data;
import ua.lillink.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserResetPasswordDto {

    @NotNull
    @NotEmpty
    @ValidEmail
    private String username;

    @NotNull
    @NotEmpty
    private String userResetPasswordToken;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String resetPassword;
}
