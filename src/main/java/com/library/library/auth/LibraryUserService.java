package com.library.library.auth;

import com.library.library.dto.LoginDto;
import com.library.library.dto.UserCreateDto;
import com.library.library.entities.Role;
import com.library.library.entities.User;
import com.library.library.repositories.RoleRepository;
import com.library.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("LibraryUserService")
public class LibraryUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void login(LoginDto dto){

    }

    public void register(UserCreateDto dto) throws Exception{
        if( userRepository.getByEmail(dto.getEmail()) != null ){
            throw new Exception("Użytkownik z takim mailem już istnieje");
        }

        User user = new User();

        user.setEmail( dto.getEmail() );
        user.setPassword( (new BCryptPasswordEncoder()).encode(dto.getPassword()) );
        user.setUsername( dto.getUsername() );
        user.addRole( roleRepository.getRoleByName(dto.getRole()) );
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());

        userRepository.save(user);
    }
}
