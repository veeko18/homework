package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.AuthorityService;
import services.CourseService;
import services.SchoolService;
import services.UserService;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CourseService courseService;//?

    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showSignupPage(@ModelAttribute("user") User user,
                                 @ModelAttribute("message") String message,
                                 @ModelAttribute("messageType") String messageType, Model model){
        model.addAttribute("school", schoolService.getAllSchool());
        model.addAttribute("course", courseService.getAllCourses());//?
        model.addAttribute("authority", authorityService.findAllAuthorities());
        return "auth/signup";
    }

    @PostMapping
    public String postLogin(User user, RedirectAttributes redirectAttributes){
        boolean doesUserExist = userService.findUserByName(user.getUsername()).isPresent();

        if(!doesUserExist){
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "Signup successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("message", "User already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/signup";//login?
        }
    }
}
