package services.implementations;

import models.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AuthorityRepository;
import services.AuthorityService;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Optional;

//implementation of authority services

@Service
public class AuthorityCourseImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void createAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public Optional<Authority> findAuthorityByName(String name) {
        return authorityRepository.findAuthorityByName(name);
    }

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }
}
