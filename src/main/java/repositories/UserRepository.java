package repositories;

//repository that handles user operations

import models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaRepository has in-build functions that can make SQL queries to database

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    Optional<User> findUserByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
