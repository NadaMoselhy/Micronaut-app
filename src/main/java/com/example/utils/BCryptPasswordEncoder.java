package com.example.utils;

import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;
@Singleton
public class BCryptPasswordEncoder {
    public String encode(String password)
    {
        return BCrypt.hashpw(password,BCrypt.gensalt());

    }

    public boolean matches(String rawPassword, String encodedPassword){
        return BCrypt.checkpw(rawPassword, encodedPassword);

    }
}
