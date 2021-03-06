package ua.lillink.resetpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ua.lillink.model.User;
import ua.lillink.service.VerificationTokenService;
import ua.lillink.service.letter.GenerateLetter;

import java.util.UUID;

@Component
public class RestorePasswordListener implements ApplicationListener<OnRestorePasswordCompleteEvent> {

    private static final String FRONT_URL = "http://localhost:4200";

    private final VerificationTokenService tokenService;
    private final GenerateLetter sendMailService;

    @Autowired
    public RestorePasswordListener(VerificationTokenService tokenService, GenerateLetter sendMailService) {
        this.tokenService = tokenService;
        this.sendMailService = sendMailService;
    }

    @Override
    public void onApplicationEvent(final OnRestorePasswordCompleteEvent event) {
        this.confirmRestorePassword(event);
    }

    private void confirmRestorePassword(final OnRestorePasswordCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        tokenService.createVerificationTokenForUser(user, token);
        final String confirmationUrl = FRONT_URL + "/confirmPassword?token=" + token;
        sendMailService.sendRestoreForgotPasswordEmail(user, confirmationUrl);
    }
}
