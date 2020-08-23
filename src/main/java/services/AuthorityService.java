package services;

import models.Authority;
import java.util.List;
import java.util.Optional;

//service to handle authority related issues

public interface AuthorityService {

    //create a new authority
    void createAuthority(Authority authority);

    //find authority by name
    Optional<Authority> findAuthorityByName(String name);

    //find all authorities
    List<Authority> findAllAuthorities();
}
