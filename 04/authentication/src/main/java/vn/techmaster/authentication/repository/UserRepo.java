package vn.techmaster.authentication.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.authentication.model.State;
import vn.techmaster.authentication.model.User;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();

    public User addUser(String fullname, String email , String hash_password){
        return addUser(fullname, email, hash_password , State.PENDING);
    }

    public User addUser(String fullname, String email , String hashed_password , State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder().id(id)
        .full_name(fullname)
        .email(email)
        .hashed_password(hashed_password)
        .state(state)
        .build();

        users.put(id, user);

        return user;
    }

    public boolean isEmailExist(String email){
       return users.values().stream().filter(users->users.getEmail().equalsIgnoreCase(email)).count() > 0;
    }

    public Optional<User> findUserByEmail(String email){
        return users.values().stream().filter(users->users.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public void updateUser(User user){
        users.put(user.getId(), user);
    }
}
