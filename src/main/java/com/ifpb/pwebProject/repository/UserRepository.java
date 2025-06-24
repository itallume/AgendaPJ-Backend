package com.ifpb.pwebProject.repository;

import com.ifpb.pwebProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

   @Query("select count(u) > 0 from User u where u.email = :email")
   public boolean emailInUse(@Param("email") String email);

   @Query("select u from User u where u.email = :email and u.password = :password")
   public User login(@Param("email") String Email, @Param("password") String password);
}
