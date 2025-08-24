package com.desafioviaappia.api.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "123456"; // sua senha real
        System.out.println("Hash gerada: " + encoder.encode(senha));
    }
}
