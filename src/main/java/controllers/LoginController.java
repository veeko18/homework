package controllers;

import models.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public String showLoginPage(@ModelAttribute("login") Login login, @ModelAttribute("message") String message,
                                @ModelAttribute("messageType") String messageType) {
        return "auth/login";
    }

    @PostMapping
    public String postLogin(Login login, RedirectAttributes redirectAttributes) {
        boolean isValid = loginService.isLoginValid(login);

        if (isValid) {
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("message", "Invalid username or password!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/login";
        }
    }
}
