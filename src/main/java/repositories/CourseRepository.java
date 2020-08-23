package repositories;

//repository that handles course operations

import models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> getAllCourses(Course course);

    Optional<Course> findCourseByName(String name);

    Optional<Course> findCourseById(Long Id);
}
