package vn.techmaster.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.authentication.exception.UserException;
import vn.techmaster.authentication.model.User;
import vn.techmaster.authentication.service.UserService;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;

@SpringBootTest
public class TestUserSevice {
    @Autowired private UserService userService;

    @Test
    public void addUser(){
        User user =  userService.addUser("tuan", "tanhtuan093@gmail.com", "123456");
        assertThat(user).isNotNull();
    }
    
    @Test
    public void login(){
        userService.addUser("tuan", "tanhtuan093@gmail.com", "123456==");
        assertThat(userService.login("tanhtuan093@gmail.com", "123456==")).isNotNull();

        assertThat(userService.login("tanhtuan093@gmail.com", "123456+")).isNull();

        assertThat(userService.login("1tanhtuan093@gmail.com", "123456==")).isNull();
    }

    @Test
    public void loginWhenAccountIsPending(){

        userService.addUser("tuan", "tanhtuan093@gmail.com", "123456==");

        assertThatThrownBy(()->{
            userService.login("tanhtuan093@gmail.com", "123456==");
        }).isInstanceOf(UserException.class)
        .hasMessageContaining("User is not active");
        
    }

    @Test
    public void loginWhenAccountIsNotFound(){
        
        assertThatThrownBy(()->{
            userService.login("tuan@gmail.com", "123456==");
        }).isInstanceOf(UserException.class)
        .hasMessageContaining("User is not found");
        
    }

    @Test
    public void loginWhenAccountIsIncorrect(){

        userService.addUserThenAutoActive("tuan", "tanhtuan093@gmail.com", "123456==");

        assertThatThrownBy(()->{
            userService.login("tanhtuan093@gmail.com", "123456");
        }).isInstanceOf(UserException.class)
        .hasMessageContaining("Password is incorrect");
        
    }
    
    @Test
    public void loginSuccessful(){
        userService.addUserThenAutoActive("tuan", "tanhtuan093@gmail.com", "123456==");
        User user = userService.login("tanhtuan093@gmail.com", "123456==");
        assertThat(user).isNotNull();
    }
}
