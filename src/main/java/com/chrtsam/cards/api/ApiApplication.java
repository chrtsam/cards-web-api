package com.chrtsam.cards.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    private static void encodePassForStorage(String pass) {
        PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
        System.out.println(bcryptEncoder.encode(pass));
    }

}
