package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String showHomePage(Model model, @ModelAttribute("message") String message,
                               @ModelAttribute("messageType") String messageType) {
        boolean isLoggedIn = false;

        if (messageType.equals("success")) {
            isLoggedIn = true;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        return "home";
    }
}
