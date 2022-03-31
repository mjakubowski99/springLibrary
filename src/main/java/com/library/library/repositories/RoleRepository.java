package com.library.library.repositories;

import com.library.library.entities.Role;
import com.library.library.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query("SELECT role FROM Role role WHERE role.name = :name")
    public Role getRoleByName(@Param("name") String name);
}
