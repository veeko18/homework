package services;

import models.User;
import java.util.List;
import java.util.Optional;

//service to handle user related operations

public interface UserService {

    //create a user
    void createUser(User user);

    //find user by username
    Optional<User> findUserByName(String username);

    //find user by username and password
    Optional<User> findByUsernameAndPassword(String username, String password);

    //get all users
    List<User> getAllUsers();
}
