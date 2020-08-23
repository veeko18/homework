package services;

//service to handle login related operations

import models.Login;

public interface LoginService {

    /*check whether login is valid or not
    *@param login login
    *@return true or false
    */

    boolean isLoginValid(Login login);
}
