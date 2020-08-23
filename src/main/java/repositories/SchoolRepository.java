package repositories;

//repository that handles school operations

import models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findSchoolByName(String name);

    Optional<School> findSchoolById(Long id);
}
