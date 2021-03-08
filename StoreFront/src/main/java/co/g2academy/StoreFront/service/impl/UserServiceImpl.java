package co.g2academy.StoreFront.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.g2academy.StoreFront.entity.User;
import co.g2academy.StoreFront.model.RegisterModel;
import co.g2academy.StoreFront.model.RegisterModelValidator;
import co.g2academy.StoreFront.repository.UserRepository;
import co.g2academy.StoreFront.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RegisterModelValidator validate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String save(RegisterModel register) {
       if(validate.validator(register)){
           User user = userRepository.findByuserName(register.getUserName());
           if (user == null) {
               User newUser = new User();
               newUser.setName(register.getName());
               newUser.setUserName(register.getUserName());
               newUser.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
               userRepository.save(newUser);
               return "Account has been saved.";
            } else {
                return "Username already Exist";
            }
        } else {
            return "Please Check your input";
        }
    }

    @Override
    public String update(RegisterModel registerModel , String userName) {
        if(validate.validator(registerModel)) {
            User user = userRepository.findByuserName(userName);
            user.setUserName(registerModel.getUserName());
            user.setName(registerModel.getName());
            user.setPassword(bCryptPasswordEncoder.encode(registerModel.getPassword()));
            userRepository.save(user);
            return "User Has Been Updated";
        } else {
            return " Please Check your input";
        }
    }
    
}
