package com.galib.springBootOauth2.users;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailRepository extends JpaRepository<Users,Integer>{
	Optional<Users> findByUsername(String username);
}
