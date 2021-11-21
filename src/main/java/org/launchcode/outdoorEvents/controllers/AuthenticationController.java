package org.launchcode.outdoorEvents.controllers;

import org.launchcode.outdoorEvents.data.UserRepository;
import org.launchcode.outdoorEvents.models.LoginFormDTO;
import org.launchcode.outdoorEvents.models.RegisterFormDTO;
import org.launchcode.outdoorEvents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    /*
     *
     *Session
     *
     */

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    /*
     *
     *Register
     *
     */

    @GetMapping("/user/register")
    public String showSignUpForm(Model model){
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "/user/register";
    }

    @PostMapping("/user/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "/user/register";
        }

        User existingUser = userRepository.findByName(registerFormDTO.getName());

        if (existingUser != null) {
            errors.rejectValue("name", "name.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "/user/register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "/user/register";
        }

        User existingEmail = userRepository.findByEmail(registerFormDTO.getEmail());

        if (existingEmail != null) {
            errors.rejectValue("email", "email.alreadyexists", "A user with that email address already exists");
            model.addAttribute("title", "Register");
            return "/user/register";
        }

        User newUser = new User(registerFormDTO.getName(), registerFormDTO.getPassword(),
                registerFormDTO.getFirstName(), registerFormDTO.getLastName(), registerFormDTO.getEmail());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:login";
    }

    /*
    *
    *Login
    *
    */

    @GetMapping("/user/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Login");
        return "/user/login";
    }

    @PostMapping("/user/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "/user/login";
        }

        User theUser = userRepository.findByName(loginFormDTO.getName());

        if (theUser == null) {
            errors.rejectValue("name", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "/user/login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "/user/login";
        }

        setUserInSession(request.getSession(), theUser);
        User currentUser = getUserFromSession(request.getSession());
        model.addAttribute("hello", "Hello, "+ currentUser.getFirstName() +" "+ currentUser.getLastName());

        return "redirect:/";
    }

    /*
     *
     *Logout
     *
     */

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/user/login";
    }

}
