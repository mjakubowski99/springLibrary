package com.library.library.commandrunners;

import com.library.library.entities.Role;
import com.library.library.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedRoles implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public void run(String... args) throws Exception {
        if( roleRepository.count() == 0 ){
            Role role = new Role();
            role.setName("User");
            roleRepository.save(role);

            role = new Role();
            role.setName("Admin");
            roleRepository.save(role);
        }
    }
}
