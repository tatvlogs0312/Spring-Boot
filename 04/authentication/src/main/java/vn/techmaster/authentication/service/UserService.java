package vn.techmaster.authentication.service;

import vn.techmaster.authentication.model.User;

public interface UserService {
    public User login(String email , String password);

    public boolean logout(String email);

    public User addUser(String fullname, String email, String password);

    public User addUserThenAutoActive(String fullname, String email, String password);

    public Boolean activateUser(String activation_code);

    public Boolean updatePassword(String email, String password);

    public User findById(String id);
}
