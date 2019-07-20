package ua.lillink.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lillink.dao.UserDao;
import ua.lillink.dao.VerificationTokenDao;
import ua.lillink.model.User;
import ua.lillink.model.VerificationToken;
import ua.lillink.service.VerificationTokenService;

import java.util.Calendar;
import java.util.Optional;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private static final String TOKEN_INVALID = "invalidToken";
    private static final String TOKEN_EXPIRED = "expired";
    private static final String TOKEN_VALID = "valid";

    private final VerificationTokenDao verificationTokenDao;
    private final UserDao userDao;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenDao verificationTokenDao, UserDao userDao) {
        this.verificationTokenDao = verificationTokenDao;
        this.userDao = userDao;
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenDao.findVerificationToken(token);
    }

    @Override
    public Optional<VerificationToken> findByUser(User user) {
        return verificationTokenDao.findByUser(user);
    }

    @Override
    public void deleteAllExpiredSince() {
        verificationTokenDao.deleteAllExpiredSince();
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenDao.save(verificationToken);
    }

    @Override
    public VerificationToken update(VerificationToken verificationToken, Long id) {
        return verificationTokenDao.update(verificationToken, id);
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        verificationTokenDao.delete(verificationToken);
    }


    public String validateVerificationToken(String token, Long id) {
        final Calendar cal = Calendar.getInstance();
        if (verificationTokenDao.findVerificationToken(token).isPresent()) {
            VerificationToken verificationToken = verificationTokenDao.findVerificationToken(token).get();
            if ((verificationToken.getExpiryDate()
                    .getTime()
                    - cal.getTime()
                    .getTime()) <= 0) {
                verificationTokenDao.delete(verificationToken);
                return TOKEN_EXPIRED;
            } else {
                User user = verificationToken.getUser();
                user.setEnabled(true);
                userDao.update(user, id);
                verificationTokenDao.delete(verificationToken);
                return TOKEN_VALID;
            }
        } else
            return TOKEN_INVALID;
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        save(verificationToken);
    }
}
