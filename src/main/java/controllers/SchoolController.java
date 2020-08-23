package controllers;

import models.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.SchoolService;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/create")
    public String showCreateSchoolPage(@ModelAttribute("school") School school,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        return "school/create-school";
    }

    @PostMapping
    public String createSchool(School school, RedirectAttributes redirectAttributes) {
        boolean doesSchoolExists = schoolService.findSchoolByName(school.getName()).isPresent();

        if (!doesSchoolExists) {
            schoolService.createSchool(school);
            redirectAttributes.addFlashAttribute("message", "School created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/school";
        } else {
            redirectAttributes.addFlashAttribute("message", "School already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/school/create";
        }
    }

    @GetMapping
    public String showSchoolList(Model model) {
        model.addAttribute("schools", schoolService.getAllSchool());
        return "school/list-school";
    }

    @GetMapping("/update/{id}")
    public String showUpdateSchoolPage(@PathVariable("id") Long id, Model model,
                                       @RequestParam(value = "school", required = false) School school,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        if (school == null) {
            schoolService.findSchoolById(id).ifPresent(foundSchool -> model.addAttribute("school", foundSchool));
        }

        return "school/update-school";
    }

    @PostMapping("/update/{id}")
    public String updateSchool(@PathVariable("id") Long id, School school, RedirectAttributes redirectAttributes) {
        boolean isSchoolExists = schoolService.findSchoolById(id).isPresent();

        if (isSchoolExists) {
            schoolService.updateSchool(school);
            redirectAttributes.addFlashAttribute("message", "School updated successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/school";
        } else {
            redirectAttributes.addFlashAttribute("message", "School not found!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/school/create";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchoolById(id);
        return "redirect:/school";
    }

    @GetMapping("/full-delete/{id}")
    public String fullDeleteSchool(@PathVariable Long id) {
        schoolService.fullDeleteSchoolById(id);
        return "redirect:/school";
    }

    @GetMapping("/restore/{id}")
    public String restoreSchool(@PathVariable Long id) {
        schoolService.restoreSchoolById(id);
        return "redirect:/school";
    }
}
