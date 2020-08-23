package repositories;

//repository that handles authority operations

import models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findAllAllAuthorities();

    Optional<Authority> findAuthorityByName(String name);
}
