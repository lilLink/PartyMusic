package ua.lillink.dao;

import ua.lillink.model.User;
import ua.lillink.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenDao extends BaseDao<VerificationToken, Long> {

    Optional<VerificationToken> findVerificationToken(String token);

    Optional<VerificationToken> findByUser(User user);

    void deleteAllExpiredSince();
}
