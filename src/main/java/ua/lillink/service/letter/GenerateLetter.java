package ua.lillink.service.letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lillink.model.Letter;
import ua.lillink.model.User;

@Service("generateService")
public class GenerateLetter {

    private final LetterService letterService;

    @Autowired
    public GenerateLetter(LetterService letterService) {
        this.letterService = letterService;
    }

    public void sendValidationEmail(User user, String linkOfValidation) {

        Letter letter = new Letter();

        letter.setEMail(user.getLogin());

        letter.setSubject("Registration on website PartyMusic");

        String content = "Your mail has been specified for registration on the site of PartyMusic " +
                "to complete the registration by clicking on the link:" + linkOfValidation +
                " If you do not know about this, ignore this message;";

        letter.setContent(content);

        letter.setWithAttachment(false);

        letterService.sendLetter(letter);

    }

    public void sendRestoreForgotPasswordEmail(User user, String linkOfValidation) {
        Letter letter = new Letter();

        letter.setEMail(user.getLogin());
        letter.setSubject("Restore password on website PartyMusic");
        String content = "Your mail has been specified for restore password on the site of PartyMusic " +
                "to complete the restore password by clicking on the link:" + linkOfValidation +
                " If you do not know about this, ignore this message;";
        letter.setContent(content);
        letter.setWithAttachment(false);

        letterService.sendLetter(letter);

    }


}
