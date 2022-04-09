package com.library.library;

import com.library.library.commandrunners.SeedRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

    @Autowired
    private SeedRoles seedRoles;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    public void run(String... args) throws Exception {
        seedRoles.run(args);
    }
}
