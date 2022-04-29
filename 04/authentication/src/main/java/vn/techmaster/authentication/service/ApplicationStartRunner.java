package vn.techmaster.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements ApplicationRunner{

    @Autowired UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.addUserThenAutoActive("Trần Anh Tuấn", "tanhtuan093@gmail.com", "123456");
        userService.addUser("Trần Anh Tuấn", "tat3122002@gmail.com", "123456");
    }
    
}
