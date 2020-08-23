package services.implementations;

import models.Login;
import org.springframework.stereotype.Service;
import services.LoginService;

//implementation of login services

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public boolean isLoginValid(Login login) {
        return false;
    }
}
