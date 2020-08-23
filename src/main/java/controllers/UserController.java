package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showAllUserPage(Model model){//?
        model.addAttribute("users", userService.getAllUsers());
        return "user/user-list";
    }

}
