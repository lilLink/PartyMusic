package ua.lillink.service.letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lillink.service.mail.MailService;

@Service("letterService")
public class LetterServiceImpl implements LetterService {

    private final MailService mailService;

    @Autowired
    public LetterServiceImpl(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void sendLetter(Object object) {
        mailService.sendEmail(object);
    }

}
