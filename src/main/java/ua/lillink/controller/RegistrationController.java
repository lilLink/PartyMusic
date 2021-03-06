package ua.lillink.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lillink.exception.ResourceNotFoundException;
import ua.lillink.model.User;
import ua.lillink.model.VerificationToken;
import ua.lillink.registration.OnRegistrationCompleteEvent;
import ua.lillink.registration.RegistrationListener;
import ua.lillink.service.UserService;
import ua.lillink.service.VerificationTokenService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private final RegistrationListener registrationListener;

    public RegistrationController(UserService userService, VerificationTokenService verificationTokenService,
                                  RegistrationListener registrationListener){
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.registrationListener = registrationListener;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getPerson(@PathVariable("id") long id){
        User user = userService.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(String.format("User with id: %d not found", id)));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/username/{login}")
    public ResponseEntity<?> getByLogin(@PathVariable("login") String login){
        return ResponseEntity.ok().body(userService.findByEmail(login).isPresent());
    }

    @PutMapping
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody User user){
        User currentUser = userService.findById(id).orElseThrow(() ->
                            new ResourceNotFoundException(String.format("User with id: %d not found", id)));
        currentUser.setLogin(user.getLogin());
        currentUser.setEnabled(user.isEnabled());
        userService.update(currentUser);
        return ResponseEntity.ok().body("User has been updated successfully.");
    }

    @GetMapping(value = "/auth/confirm")
    public String confirmRegistration(@RequestParam("token") final String token) {
        final String result = verificationTokenService.validateVerificationToken(token);
        if (result.equals("valid")) {
            verificationTokenService.findByToken(token).ifPresent(verificationTokenService::delete);
        }
        return result;
    }

    @GetMapping(value = "/findToken")
    public String findToken(@RequestParam("email") String email) {
        User user = userService.findByEmail(email).orElse(new User());
        VerificationToken verificationToken = verificationTokenService.findByUser(user).orElse(new VerificationToken());
        return verificationTokenService.validateVerificationToken(verificationToken.getToken());
    }

    @PostMapping(value = "/resendAuthToken")
    public ResponseEntity<String> resendRegistrationToken(HttpServletRequest request, @RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("Email not found!");
        } else {
            verificationTokenService.findByUser(user.get()).ifPresent(verificationTokenService::delete);
            registrationListener.onApplicationEvent(new OnRegistrationCompleteEvent(user.get(), getAppUrl(request)));
            return ResponseEntity.ok().body("Successfully!");
        }
    }

    @RequestMapping(value = "/enabled/{email}/", method = RequestMethod.GET)
    public ResponseEntity<?> enabledUser(@PathVariable("email") String email) {
        if (userService.findByEmail(email).isPresent()) {
            User user = userService.findByEmail(email).get();
            return ResponseEntity.ok(user.isEnabled());
        } else {
            return ResponseEntity.ok().body("User not found!");
        }
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
