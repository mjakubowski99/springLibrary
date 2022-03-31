package com.library.library.services;

import com.library.library.auth.exceptions.NotExistsException;
import com.library.library.auth.exceptions.UserAlreadyExistsException;
import com.library.library.entities.Role;
import com.library.library.entities.User;
import com.library.library.repositories.RoleRepository;
import com.library.library.repositories.UserRepository;
import com.library.library.requests.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    public boolean userWithUsernameExists(String username){
        return (userRepository.getUserByUsername(username) != null);
    }

    public boolean roleExists(String roleName){
        return (roleRepository.getRoleByName(roleName) != null );
    }

    public boolean userWithEmailExists(String email){
        return (userRepository.getByEmail(email) != null);
    }

    public User createUser(UserCreateRequest request) throws UserAlreadyExistsException, NotExistsException{
        if( userWithEmailExists(request.getEmail()) || userWithUsernameExists(request.getUsername()) ){
            throw new UserAlreadyExistsException();
        }

        if( !roleExists(request.getRole()) ){
            throw new NotExistsException("This role does not exists");
        }

        Role role = roleRepository.getRoleByName(request.getRole());
        User user = new User();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setEmail( request.getEmail() );
        user.setUsername( request.getUsername() );
        user.setPassword( bCryptPasswordEncoder.encode(request.getPassword()) );
        user.addRole(role);

        userRepository.save(user);

        return user;
    }
}
