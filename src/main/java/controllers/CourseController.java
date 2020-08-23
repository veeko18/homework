package controllers;

import models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.CourseService;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/create")
    public String showCreateCoursePage(@ModelAttribute("course") Course course,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        return "course/create-course";
    }

    @PostMapping
    public String createCourse(Course course, RedirectAttributes redirectAttributes) {
        boolean doesCourseExists = courseService.findCourseByName(course.getName()).isPresent();

        if (!doesCourseExists) {
            courseService.createCourse(course);
            redirectAttributes.addFlashAttribute("message", "Course created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/course";
        } else {
            redirectAttributes.addFlashAttribute("message", "Course already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/course/create";
        }
    }

    @GetMapping
    public String showCourseList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/list-course";
    }

    @GetMapping("/update/{id}")
    public String showUpdateCoursePage(@PathVariable("id") Long id, Model model,
                                       @RequestParam(value = "course", required = false) Course course,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        if (course == null) {
            courseService.findCourseById(id).ifPresent(foundCourse -> model.addAttribute("course", foundCourse));
        }

        return "course/update-course";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, Course course, RedirectAttributes redirectAttributes) {
        boolean doesCourseExists = courseService.findCourseById(id).isPresent();

        if (doesCourseExists) {
            courseService.updateCourse(course);
            redirectAttributes.addFlashAttribute("message", "Course updated successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/course";
        } else {
            redirectAttributes.addFlashAttribute("message", "Course not found!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/course/create";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/course";
    }

    @GetMapping("/full-delete/{id}")
    public String fullDeleteCourse(@PathVariable Long id) {
        courseService.fullDeleteCourseById(id);
        return "redirect:/course";
    }

    @GetMapping("/restore/{id}")
    public String restoreCourse(@PathVariable Long id) {
        courseService.restoreCourseById(id);
        return "redirect:/course";
    }
}
