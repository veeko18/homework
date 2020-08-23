package services.implementations;

import models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CourseRepository;
import services.CourseService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//implementation of course services

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getActiveCourses() {
        return getAllCourses().stream()
                .filter(Course::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Course> findCourseByName(String name) {
        return courseRepository.findCourseByName(name);
    }

    @Override
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void deleteCourseById(Long id) {
        findCourseById(id).ifPresent(course -> {
            course.setActive(false);
            updateCourse(course);
        });
    }

    @Override
    public void fullDeleteCourseById(Long id) {
        findCourseById(id).ifPresent(course -> {
            courseRepository.delete(course);
        });
    }

    @Override
    public void restoreCourseById(Long id) {
        findCourseById(id).ifPresent(course -> {
            course.setActive(true);
            updateCourse(course);
        });
    }
}
