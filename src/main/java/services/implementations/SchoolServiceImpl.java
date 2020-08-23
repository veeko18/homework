package services.implementations;

import models.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.SchoolRepository;
import services.SchoolService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//implementation of school services

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public void createSchool(School school) {
        school.setActive(true);
        schoolRepository.save(school);
    }

    @Override
    public List<School> getAllSchool() {
        return schoolRepository.findAll();
    }

    @Override
    public List<School> getActiveSchools() {
        return getAllSchool().stream().filter(School::isActive)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<School> findSchoolByName(String name) {
        return schoolRepository.findSchoolByName(name);
    }

    @Override
    public Optional<School> findSchoolById(Long id) {
        return schoolRepository.findSchoolById(id);
    }

    @Override
    public void updateSchool(School school) {
        schoolRepository.saveAndFlush(school);
    }

    @Override
    public void deleteSchoolById(Long id) {
        findSchoolById(id).ifPresent(school -> {
            school.setActive(false);
            updateSchool(school);
        });
    }

    @Override
    public void fullDeleteSchoolById(Long id) {
        findSchoolById(id).ifPresent(school -> {
            schoolRepository.delete(school);
        });
    }

    @Override
    public void restoreSchoolById(Long id) {
        findSchoolById(id).ifPresent(school -> {
            school.setActive(true);
            updateSchool(school);
        });
    }
}
