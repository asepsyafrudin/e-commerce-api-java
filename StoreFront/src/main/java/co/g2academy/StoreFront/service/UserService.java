package co.g2academy.StoreFront.service;

import co.g2academy.StoreFront.model.RegisterModel;

public interface UserService {
    public String save(RegisterModel registerModel);
    public String update(RegisterModel registerModel , String userName);
}
