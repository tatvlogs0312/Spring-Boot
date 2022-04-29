package vn.techmaster.authentication.service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import vn.techmaster.authentication.exception.UserException;
import vn.techmaster.authentication.hash.Hashing;
import vn.techmaster.authentication.model.State;
import vn.techmaster.authentication.model.User;
import vn.techmaster.authentication.repository.UserRepo;

@Service
public class UserServiceInMemory implements UserService{
    private UserRepo userRepo;
    private Hashing hashing;
    private ConcurrentHashMap<String , String> activate_code_user_ids = new ConcurrentHashMap<>();

    public UserServiceInMemory(UserRepo userRepo, Hashing hashing) {
        this.userRepo = userRepo;
        this.hashing = hashing;
    }

    @Override
    public User login(String email, String password) {
        Optional<User> o_user = userRepo.findUserByEmail(email);

        // Nếu user ko tồn tại thì báo lỗi
        if(!o_user.isPresent()){
            throw new UserException("User is not found");
        }

        User user = o_user.get();
        // User muốn login thì trạng thái phải là active
        if(user.getState() != State.ACTIVE){
            throw new UserException("User is not active");
        }

        // Kiểm tra password
        if(hashing.validatePassword(password, user.getHashed_password())){
            return user;
        }
        else{
            throw new UserException("Password is incorrect");
        }
    }

    @Override
    public boolean logout(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User addUser(String fullname, String email, String password) {
        
        return userRepo.addUser(fullname, email, hashing.hashPassword(password));
    }

    @Override
    public User addUserThenAutoActive(String fullname, String email, String password) {

        return userRepo.addUser(fullname, email, hashing.hashPassword(password),State.ACTIVE);

    }

    @Override
    public Boolean activateUser(String activation_code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
