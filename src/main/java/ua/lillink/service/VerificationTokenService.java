package ua.lillink.service;

import ua.lillink.model.User;
import ua.lillink.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByUser(User user);

    void deleteAllExpiredSince();

    VerificationToken save(VerificationToken verificationToken);

    VerificationToken update(VerificationToken verificationToken);

    void delete(VerificationToken verificationToken);

    String validateVerificationToken(String token);

    void createVerificationTokenForUser(User user, String token);
}
