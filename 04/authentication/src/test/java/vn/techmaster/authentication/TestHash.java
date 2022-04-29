package vn.techmaster.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.authentication.hash.Hashing;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class TestHash {
    @Autowired private Hashing hash;

    @Test
    public void hashPassword(){
        var passwords = List.of("abcd121+-","dfghjk_","132466i7","oc_12ews");
        for(String password : passwords){
            String hash_pass = hash.hashPassword(password);
            assertThat(hash_pass).isNotNull();
        }
    }

    @Test
    public void validatePassword(){
        var passwords = List.of("abcd121+-","dfghjk_","132466i7","oc_12ews");
        for(String password : passwords){
            String hash_pass = hash.hashPassword(password);
            assertThat(hash.validatePassword(password, hash_pass)).isTrue();
        }

        
    }
}
