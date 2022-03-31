package com.library.library.commandrunners;

import com.library.library.entities.Role;
import com.library.library.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;

public class SeedRoles implements CommandLineRunner {

    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if( roleRepository.count() == 0 ){
            Role role = new Role();
            role.setName("User");
            roleRepository.save(role);
        }
    }
}
