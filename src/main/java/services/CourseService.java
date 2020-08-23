package services;

import models.Course;
import models.School;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    //create a new course
    void createCourse(Course course);

    //get all courses
    List<Course> getAllCourses();

    //get list of active courses
    List<Course> getActiveCourses();

    //find course by name
    Optional<Course> findCourseByName(String name);

    //find course by id
    Optional<Course> findCourseById(Long id);

    //update existing course
    void updateCourse(Course course);

    //delete course by id
    void deleteCourseById(Long id);

    //delete course from database completely
    void fullDeleteCourseById(Long id);

    //restore course by id
    void restoreCourseById(Long id);
}
