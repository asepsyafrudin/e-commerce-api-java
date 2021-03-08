package co.g2academy.StoreFront.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class RegisterModelValidator {
    public Boolean validator (RegisterModel r) {
        if(checkPasswordAndConfirmPasswordisSame(r.getPassword(), r.getConfirmPassword())== true &&
        checkUserNameUseEmailAddress(r.getUserName()) ==  true) {
            return true;
        }
        return false;
    }

    public Boolean checkPasswordAndConfirmPasswordisSame(String password, String confirmPassword ) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    public Boolean checkUserNameUseEmailAddress(String userName) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher mathcer = pattern.matcher(userName);
        return mathcer.matches();
    }
    
}
