package com.galib.springBootOauth2.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.jivita.rammps.config.*;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

}
