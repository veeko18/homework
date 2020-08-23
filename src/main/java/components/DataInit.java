package components;

import models.Authority;
import models.Course;
import models.School;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.AuthorityService;
import services.CourseService;
import services.SchoolService;
import services.UserService;
import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.zip.CheckedOutputStream;

//A helper class to hold constant values of the application

@Component
public class DataInit {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CourseService courseService;

    @PostConstruct
    public void initData() {
        initSchoolData();
        initAuthorityData();
        initUserData();
        initCourseData();
    }

    // PRIVATE METHODS //
    private void initUserData() {
        Optional<Authority> optionalAuthority = authorityService.findAuthorityByName(AUTHORITY_ADMIN);
        Optional<School> optionalSchool = schoolService.findSchoolByName("Tallinn International school");

        if (optionalAuthority.isPresent() && optionalSchool.isPresent()) {
            User user = new User();
            user.setUsername("admin@study.com");
            user.setPassword("123456");
            user.setSchool(optionalSchool.get());
            user.setAuthority(optionalAuthority.get());

            if (userService.findUserByName(user.getUsername()).isPresent()) {
                userService.createUser(user);
            }
        }
    }

    private void initAuthorityData() {
        Authority authorityAdmin = new Authority();
        authorityAdmin.setName(AUTHORITY_ADMIN);
        createAuthority(authorityAdmin);

        Authority authorityTeacher = new Authority();
        authorityTeacher.setName(AUTHORITY_TEACHER);
        createAuthority(authorityTeacher);

        Authority authorityStudent = new Authority();
        authorityStudent.setName(AUTHORITY_STUDENT);
        createAuthority(authorityStudent);

    }

    private void initSchoolData() {
        School school = new School();
        school.setName("Tallinn International school");
        school.setCity("Tallinn");
        school.setPhone("94856735");

        if (schoolService.findSchoolByName(school.getName()).isEmpty()) {
            schoolService.createSchool(school);
        }
    }

    private void createAuthority(Authority authority) {
        if (authorityService.findAuthorityByName(authority.getName()).isEmpty()) {
            authorityService.createAuthority(authority);
        }
    }

    private void initCourseData() {
        Course course = new Course();
        course.setName("Java Tallinn 10");
        course.setDurationHours(7.0);

        if(courseService.findCourseByName(course.getName()).isEmpty()){
            courseService.createCourse(course);
        }
    }

}
