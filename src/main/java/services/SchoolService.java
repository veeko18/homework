package services;

import models.School;
import java.util.List;
import java.util.Optional;

//services to handle school operations

public interface SchoolService {

    //create a new school
    void createSchool(School school);

    //get list of all schools
    List<School> getAllSchool();

    //get list of active schools
    List<School> getActiveSchools();

    //find school by name
    Optional<School> findSchoolByName(String name);

    //find school by id
    Optional<School> findSchoolById(Long id);

    //update existing school
    void updateSchool(School school);

    //delete school by id
    void deleteSchoolById(Long id);

    //delete school from database completely
    void fullDeleteSchoolById(Long id);

    //restore school by id
    void restoreSchoolById(Long id);
}
